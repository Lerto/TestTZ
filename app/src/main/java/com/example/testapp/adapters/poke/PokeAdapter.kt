package com.example.testapp.adapters.poke

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.model.Model
import java.util.*

class PokeAdapter(
    private var objects: ArrayList<Model.Poke>,
) : RecyclerView.Adapter<PokeViewAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PokeViewAdapter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_poke, parent, false)
        )

    override fun getItemCount() = objects.size

    override fun onBindViewHolder(holder: PokeViewAdapter, position: Int) {
        holder.bind(objects[position], position)
    }
}