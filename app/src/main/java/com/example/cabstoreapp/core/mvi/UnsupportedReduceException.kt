package com.example.cabstoreapp.core.mvi

import com.example.cabstoreapp.core.mvi.events.MviResult
import com.example.cabstoreapp.core.mvi.events.MviUiState

class UnsupportedReduceException(state: MviUiState, result: MviResult) :
    RuntimeException("Cannot reduce state: $state with result: $result")
