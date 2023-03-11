package com.example.cabstoreapp.data

import com.example.cabstoreapp.data.mapper.DataResponseMapper
import com.example.cabstoreapp.data.source.ProductRemote
import com.example.cabstoreapp.domain.model.DomainProductList
import com.example.cabstoreapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductDataRepository @Inject constructor(
    private val remote: ProductRemote,
    private val mapper: DataResponseMapper
) : ProductRepository {

    override fun getProductList(): Flow<DomainProductList> = flow {
        val recipeList = with(mapper) {
            remote.getProductList().toDomain()
        }
        emit(recipeList)
    }
}
