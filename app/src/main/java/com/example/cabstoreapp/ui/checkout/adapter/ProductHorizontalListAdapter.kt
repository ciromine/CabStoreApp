package com.example.cabstoreapp.ui.checkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cabstoreapp.domain.model.DomainProduct

class ProductHorizontalListAdapter(
    private val items: MutableList<DomainProduct>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ProductHorizontalViewHolder.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHorizontalViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val summary = items[position]
        (holder as ProductHorizontalViewHolder).bind(summary)
    }
}