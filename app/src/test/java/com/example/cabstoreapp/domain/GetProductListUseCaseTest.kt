package com.example.cabstoreapp.domain

import com.example.cabstoreapp.data.mapper.DataResponseMapper
import com.example.cabstoreapp.data.remote.model.ProductListResponse
import com.example.cabstoreapp.data.remote.model.ProductResponse
import junit.framework.Assert.assertEquals
import org.junit.Test

internal class GetProductListUseCaseTest {
    private val mapper = DataResponseMapper()

    @Test
    fun `given ProductListResponse when toDomain(), then DomainProductList`() {
        val remoteProductListResponse = ProductListResponse(
            listOf(
                ProductResponse(
                    "title",
                    "description",
                    1.0
                )
            )
        )

        val domainProductList = with(mapper) {
            remoteProductListResponse.toDomain()
        }

        assertEquals(domainProductList.products.first().code, remoteProductListResponse.products.first().code)
        assertEquals(domainProductList.products.first().name, remoteProductListResponse.products.first().name)
        assertEquals(domainProductList.products.first().price, remoteProductListResponse.products.first().price)
    }
}