package com.example.testapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.model.Model
import java.util.*

class SomeAdapter(
    internal var objects: ArrayList<Model.Some>,
) : RecyclerView.Adapter<SomeViewAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SomeViewAdapter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_some, parent, false)
        )

    override fun getItemCount() = objects.size

    override fun onBindViewHolder(holder: SomeViewAdapter, position: Int) {
        holder.bind(objects[position], position)
    }
}