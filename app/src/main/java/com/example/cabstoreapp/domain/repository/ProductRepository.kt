package com.example.cabstoreapp.domain.repository

import com.example.cabstoreapp.domain.model.DomainProductList
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProductList(): Flow<DomainProductList>
}
