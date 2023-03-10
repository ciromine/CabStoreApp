package com.example.cabstoreapp.presentation.detail

import com.example.cabstoreapp.core.mvi.events.MviUiState
import com.example.cabstoreapp.domain.model.DomainProduct

sealed class DetailUiState : MviUiState {
    object DefaultUiState : DetailUiState()
    object LoadingUiState : DetailUiState()
    data class SuccessUiState(val products: List<DomainProduct>) : DetailUiState()
    object ErrorUiState : DetailUiState()
}