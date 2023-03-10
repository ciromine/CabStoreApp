package com.example.cabstoreapp.core

import com.example.cabstoreapp.domain.model.DomainProduct

object ShopCart {
    private val elements: MutableList<DomainProduct> = ArrayList()

    fun addProductToCart(domainProduct: DomainProduct, quantity: Int) {
        for (index in 1..quantity) {
            elements.add(domainProduct)
        }
    }

    fun getProductsFromCart() : MutableList<DomainProduct> {
        return elements
    }

    fun cleanCart() {
        elements.clear()
    }
}