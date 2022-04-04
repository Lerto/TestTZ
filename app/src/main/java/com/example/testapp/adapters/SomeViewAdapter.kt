package com.example.testapp.adapters


import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.model.Model

class SomeViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n")
    fun bind(
        item: Model.Some,
        pos: Int
    ) {

    }
}