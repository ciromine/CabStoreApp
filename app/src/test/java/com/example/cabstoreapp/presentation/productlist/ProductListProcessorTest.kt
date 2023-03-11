package com.example.cabstoreapp.presentation.productlist

import com.example.cabstoreapp.core.execution.FakeCoroutineExecutionThread
import com.example.cabstoreapp.domain.GetProductListUseCase
import com.example.cabstoreapp.domain.model.DomainProduct
import com.example.cabstoreapp.domain.model.DomainProductList
import com.example.cabstoreapp.presentation.productlist.ProductListAction.GoToDetailAction
import com.example.cabstoreapp.presentation.productlist.ProductListResult.GetProductListResult.InProgress
import com.example.cabstoreapp.presentation.productlist.ProductListResult.GetProductListResult.Success
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class ProductListProcessorTest {
    private val getProductListUseCase = mockk<GetProductListUseCase>(relaxed = true)

    private val processor =
        ProductListProcessor(getProductListUseCase, FakeCoroutineExecutionThread())

    private val stubDomainProduct = DomainProduct(
        "",
        "",
        1.0
    )

    private val stubDomainProductList = DomainProductList(
        products = listOf(stubDomainProduct)
    )

    @Test
    fun `given GetProductListAction and use case with DomainProductList, when calls 'actionProcessor', then return GetProductListResult-Success`() =
        runBlocking {
            stubGetProductListUseCase(stubDomainProductList)

            val results = processor.actionProcessor(ProductListAction.GetProductListAction).toList()

            assertEquals(results[0], InProgress)
            assertEquals(results[1],
                Success(stubDomainProductList.products)
            )
        }

    @Test
    fun `given GoToDetailAction, when calls 'actionProcessor', then return NavigateToResult-GoToDetail`() =
        runBlocking {
            val results = processor.actionProcessor(
                GoToDetailAction(
                    DomainProduct(
                        "",
                        "",
                        1.0
                    )
                )
            ).toList()

            assertEquals(
                results[0], ProductListResult.NavigateToResult.GoToDetail(
                    DomainProduct(
                        "",
                        "",
                        1.0
                    )
                )
            )
        }

    private fun stubGetProductListUseCase(domainProductList: DomainProductList) {
        coEvery { getProductListUseCase.execute() } returns flow { emit(domainProductList) }
    }
}
