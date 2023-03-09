package com.example.cabstoreapp.presentation.productlist

import com.example.cabstoreapp.core.mvi.events.MviUiState
import com.example.cabstoreapp.domain.model.DomainProduct

sealed class ProductListUiState : MviUiState {
    object DefaultUiState : ProductListUiState()
    object LoadingUiState : ProductListUiState()
    data class SuccessUiState(val products: List<DomainProduct>) : ProductListUiState()
    object ErrorUiState : ProductListUiState()
}
