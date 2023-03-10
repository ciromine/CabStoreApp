package com.example.cabstoreapp.presentation.detail

import com.example.cabstoreapp.core.mvi.MviReducer
import com.example.cabstoreapp.core.mvi.UnsupportedReduceException
import javax.inject.Inject

class DetailReducer @Inject constructor() :
    MviReducer<DetailUiState, DetailResult> {

    override fun DetailUiState.reduce(result: DetailResult): DetailUiState {
        return when (val previousState = this) {
            is DetailUiState.DefaultUiState -> previousState reduce result
            is DetailUiState.LoadingUiState -> previousState reduce result
            is DetailUiState.SuccessUiState -> previousState reduce result
            is DetailUiState.ErrorUiState -> previousState reduce result
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun DetailUiState.DefaultUiState.reduce(result: DetailResult): DetailUiState {
        return when (result) {
            DetailResult.GetDetailResult.InProgress -> DetailUiState.LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun DetailUiState.LoadingUiState.reduce(result: DetailResult): DetailUiState {
        return when (result) {
            is DetailResult.GetDetailResult.Success -> DetailUiState.SuccessUiState(
                result.results
            )
            is Error -> DetailUiState.ErrorUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun DetailUiState.SuccessUiState.reduce(result: DetailResult): DetailUiState {
        return when (result) {
            is DetailResult.NavigateToResult.GoToDetail -> this
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun DetailUiState.ErrorUiState.reduce(result: DetailResult): DetailUiState {
        return when (result) {
            DetailResult.GetDetailResult.InProgress -> DetailUiState.LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }
}