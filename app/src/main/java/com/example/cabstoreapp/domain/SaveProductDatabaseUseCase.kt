package com.example.cabstoreapp.domain

import com.example.cabstoreapp.domain.model.DomainProductList
import com.example.cabstoreapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveProductDatabaseUseCase
@Inject
constructor(private val repository: ProductRepository) {
    fun execute(): Flow<DomainProductList> = repository.getProductList()
}