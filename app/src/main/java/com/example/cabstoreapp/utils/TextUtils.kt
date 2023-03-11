package com.example.cabstoreapp.utils

import com.example.cabstoreapp.domain.model.DomainProduct

class TextUtils {
    companion object {
        fun generateDetailText(products: MutableList<DomainProduct>): String {
            var result = ""
            products.sortBy { it.name }
            products.forEachIndexed { index, element ->
                result = if (index == 0) {
                    element.name
                } else {
                    "${result}\n${element.name}"
                }
            }

            return result
        }
    }
}