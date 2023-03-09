package com.example.cabstoreapp.presentation.productlist

import com.example.cabstoreapp.core.mvi.MviReducer
import com.example.cabstoreapp.core.mvi.UnsupportedReduceException
import com.example.cabstoreapp.presentation.productlist.ProductListResult.GetProductListResult.InProgress
import com.example.cabstoreapp.presentation.productlist.ProductListResult.GetProductListResult.Success
import com.example.cabstoreapp.presentation.productlist.ProductListResult.NavigateToResult.GoToDetail
import com.example.cabstoreapp.presentation.productlist.ProductListUiState.*
import javax.inject.Inject

class ProductListReducer @Inject constructor() :
    MviReducer<ProductListUiState, ProductListResult> {

    override fun ProductListUiState.reduce(result: ProductListResult): ProductListUiState {
        return when (val previousState = this) {
            is DefaultUiState -> previousState reduce result
            is LoadingUiState -> previousState reduce result
            is SuccessUiState -> previousState reduce result
            is ErrorUiState -> previousState reduce result
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun DefaultUiState.reduce(result: ProductListResult): ProductListUiState {
        return when (result) {
            InProgress -> LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun LoadingUiState.reduce(result: ProductListResult): ProductListUiState {
        return when (result) {
            is Success -> SuccessUiState(result.results)
            is Error -> ErrorUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun SuccessUiState.reduce(result: ProductListResult): ProductListUiState {
        return when (result) {
            is GoToDetail -> this
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun ErrorUiState.reduce(result: ProductListResult): ProductListUiState {
        return when (result) {
            InProgress -> LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }
}

