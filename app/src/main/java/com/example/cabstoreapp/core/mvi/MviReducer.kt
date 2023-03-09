package com.example.cabstoreapp.core.mvi

import com.example.cabstoreapp.core.mvi.events.MviResult
import com.example.cabstoreapp.core.mvi.events.MviUiState

interface MviReducer<TUiState : MviUiState, TResult: MviResult> {

    infix fun TUiState.reduce(result: TResult): TUiState
}
