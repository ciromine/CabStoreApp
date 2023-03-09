package com.example.cabstoreapp.data.mapper

import com.example.cabstoreapp.data.local.ProductEntity
import com.example.cabstoreapp.domain.model.DomainProduct
import javax.inject.Inject

class DataEntityMapper @Inject constructor() {

    private fun ProductEntity.toDomainItem() = DomainProduct(
        code = code,
        name = name,
        price = price
    )
}