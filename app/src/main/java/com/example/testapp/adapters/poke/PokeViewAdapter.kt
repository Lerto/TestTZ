package com.example.testapp.adapters.poke


import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.model.Model
import com.example.testapp.ui.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_poke.view.*

class PokeViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imagePoke = itemView.item_poke_image
    private val namePoke = itemView.item_poke_name

    @SuppressLint("SetTextI18n")
    fun bind(
        item: Model.Poke,
        pos: Int
    ) {
        namePoke.text = item.name

        var path = item.sprites.front_default
        if (path.isNullOrEmpty()) path = "some"

        Picasso.with(itemView.context)
            .load(path)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(imagePoke)

        itemView.setOnClickListener {
            (itemView.context as MainActivity).upBottom(item)
        }
    }
}