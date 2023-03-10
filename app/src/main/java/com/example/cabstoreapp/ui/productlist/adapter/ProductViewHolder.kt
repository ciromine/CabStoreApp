package com.example.cabstoreapp.ui.productlist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.cabstoreapp.databinding.ViewItemProductBinding
import com.example.cabstoreapp.domain.model.DomainProduct

class ProductViewHolder(val binding: ViewItemProductBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(attrs: DomainProduct, onClickListener: (DomainProduct) -> Unit) {
        binding.apply {
            title.text = attrs.name
            root.setOnClickListener { onClickListener.invoke(attrs) }
        }
    }
}
