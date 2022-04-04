package com.example.testapp.adapters.pokestats


import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.model.Model
import kotlinx.android.synthetic.main.item_poke_stats.view.*

class PokeStatsViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val stat = itemView.item_poke_stats_stat

    @SuppressLint("SetTextI18n")
    fun bind(
        item: Model.Stats,
        pos: Int
    ) {
        Log.d("LOGLOGLOG", "STAST")
        stat.text = "${item.stat.name}: ${item.base_stat}"
    }
}