package com.example.testapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.R
import com.example.testapp.adapters.poke.PokeAdapter
import com.example.testapp.adapters.pokestats.PokeStatsAdapter
import com.example.testapp.api.APIService
import com.example.testapp.model.Model
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main_bottom_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var bSBPoke: BottomSheetBehavior<View>

    private val listPoke = ArrayList<Model.Poke>()

    val adapterPoke = PokeAdapter(listPoke)
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bSBPoke = BottomSheetBehavior.from(main_bottom_poke_detail)

        setListeners()
        setPoke()
        for (i in 1..10) {
            getPoke(i)
        }
    }

    private fun getPoke(id: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val req = APIService.api(this@MainActivity).getPoke(id)
                if (req.isSuccessful) {
                    req.body()?.let {
                        listPoke.add(it)

                        adapterPoke.notifyItemInserted(count++)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Ошибка при загрузке данных", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Ошибка при загрузке данных", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setPoke() {
        main_list.adapter = adapterPoke
        main_list.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun upBottom(item: Model.Poke) {
        //images
        var pathFront = item.sprites.front_default
        if (pathFront.isNullOrEmpty()) pathFront = "some"

        var pathBack = item.sprites.back_default
        if (pathBack.isNullOrEmpty()) pathBack = "some"

        Picasso.with(this)
            .load(pathFront)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(main_bottom_image_front)

        Picasso.with(this)
            .load(pathBack)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(main_bottom_image_back)

        //stats
        val adapterStats = PokeStatsAdapter(item.stats)
        main_bottom_poke_list_stats.adapter = adapterStats
        main_bottom_poke_list_stats.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //other
        main_bottom_poke_name.text = item.name

        //up
        setBSBCallback()
        bSBPoke.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun setListeners() {
        main_bg.setOnClickListener {
            bSBPoke.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun setBSBCallback() {
        bSBPoke.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    main_bg.visibility = View.GONE
                    bSBPoke.removeBottomSheetCallback(this)
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                main_bg.visibility = View.VISIBLE
                main_bg.alpha = slideOffset
                main_bottom_poke_detail.alpha = slideOffset
            }
        })
    }
}