package com.example.cabstoreapp.data

import com.example.cabstoreapp.data.mapper.DataResponseMapper
import com.example.cabstoreapp.data.remote.model.ProductListResponse
import com.example.cabstoreapp.data.remote.model.ProductResponse
import com.example.cabstoreapp.data.source.ProductRemote
import com.example.cabstoreapp.domain.model.DomainProduct
import com.example.cabstoreapp.domain.model.DomainProductList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
internal class ProductDataRepositoryTest {
    private val remote = mockk<ProductRemote>()
    private val dataResponseMapper = mockk<DataResponseMapper>()

    private val repository = ProductDataRepository(remote, dataResponseMapper)

    private val remoteProductListResponse = ProductListResponse(
        products = listOf(
            ProductResponse(
                "", "", 1.0
            )
        )
    )

    private val domainProductList = DomainProductList(
        products = listOf(
            DomainProduct(
                "",
                "",
                1.0
            )
        )
    )

    @Test
    fun `given remote when getProductList then Completes`() = runBlocking {
        stubGetProductList(remoteProductListResponse)
        stubDataResponseMapper(remoteProductListResponse, domainProductList)

        repository.getProductList().collect { result ->
            assertEquals(domainProductList, result)
        }

        coVerify { remote.getProductList() }
    }

    private fun stubGetProductList(remoteProductListResponse: ProductListResponse) {
        coEvery { remote.getProductList() } returns remoteProductListResponse
    }

    private fun stubDataResponseMapper(
        remote: ProductListResponse,
        domain: DomainProductList
    ) {
        every { with(dataResponseMapper) { remote.toDomain() } } returns domain
    }
}