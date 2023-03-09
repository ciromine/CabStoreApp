package com.example.cabstoreapp.data.mapper

import com.example.cabstoreapp.data.remote.model.ProductListResponse
import com.example.cabstoreapp.data.remote.model.ProductResponse
import com.example.cabstoreapp.domain.model.DomainProduct
import com.example.cabstoreapp.domain.model.DomainProductList
import javax.inject.Inject

class DataResponseMapper @Inject constructor() {

    fun ProductListResponse.toDomain() = DomainProductList(
        products = products.map { it.toDomainItem() }
    )

    private fun ProductResponse.toDomainItem() = DomainProduct(
        code = code,
        name = name,
        price = price
    )
}