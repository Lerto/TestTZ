package com.example.testapp.adapters.pokestats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.model.Model

class PokeStatsAdapter(
    private var objects: ArrayList<Model.Stats>,
) : RecyclerView.Adapter<PokeStatsViewAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PokeStatsViewAdapter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_poke_stats, parent, false)
        )

    override fun getItemCount() = objects.size

    override fun onBindViewHolder(holder: PokeStatsViewAdapter, position: Int) {
        holder.bind(objects[position], position)
    }
}