package com.example.cabstoreapp.presentation.productlist

import com.example.cabstoreapp.domain.model.DomainProduct
import com.example.cabstoreapp.presentation.productlist.ProductListResult.GetProductListResult.InProgress
import com.example.cabstoreapp.presentation.productlist.ProductListResult.GetProductListResult.Success
import com.example.cabstoreapp.presentation.productlist.ProductListResult.NavigateToResult.GoToDetail
import com.example.cabstoreapp.presentation.productlist.ProductListUiState.*
import org.junit.Test

internal class ProductListReducerTest {
    private val sutReducer = ProductListReducer()

    @Test
    fun `given DefaultUiState with GetCharacterListResult-InProgress, when reduce, then return LoadingUiState`() {
        val previousState = ProductListUiState.DefaultUiState
        val result = InProgress

        val newState = with(sutReducer) { previousState reduce result }

        assert(newState is LoadingUiState)
    }

    @Test
    fun `given LoadingUiState with GetCharacterListResult-Success, when reduce, then return SuccessUiState`() {
        val previousState = LoadingUiState
        val result = Success(listOf())

        val newState = with(sutReducer) { previousState reduce result }

        assert(newState is SuccessUiState)
    }

    @Test
    fun `given SuccessUiState with NavigateToResult-GoToDetail, when reduce, then return SuccessUiState`() {
        val previousState = SuccessUiState(listOf())
        val result = GoToDetail(
            DomainProduct(
                "",
                "",
                1.0
            )
        )

        val newState = with(sutReducer) { previousState reduce result }

        assert(newState is SuccessUiState)
    }

    @Test
    fun `given ErrorUiState with GetCharacterListResult-InProgress, when reduce, then return LoadingUiState`() {
        val previousState = ErrorUiState
        val result = InProgress

        val newState = with(sutReducer) { previousState reduce result }

        assert(newState is LoadingUiState)
    }
}
