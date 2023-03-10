package com.example.cabstoreapp.ui.checkout.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.cabstoreapp.databinding.ViewItemProductHorizontalBinding
import com.example.cabstoreapp.domain.model.DomainProduct

class ProductHorizontalViewHolder(val binding: ViewItemProductHorizontalBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(attrs: DomainProduct) {
        binding.apply {
            title.text = attrs.name
        }
    }
}