package com.example.cabstoreapp.ui.productlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cabstoreapp.databinding.ViewItemRecipeBinding
import com.example.cabstoreapp.domain.model.DomainProduct

class ProductListAdapter(
    private val items: MutableList<DomainProduct>,
    private val onItemClickListener: (DomainProduct) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ViewItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val summary = items[position]
        (holder as ProductViewHolder).bind(summary, onItemClickListener)
    }
}