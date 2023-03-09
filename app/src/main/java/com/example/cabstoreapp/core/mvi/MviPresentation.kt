package com.example.cabstoreapp.core.mvi

import com.example.cabstoreapp.core.mvi.events.MviUiState
import com.example.cabstoreapp.core.mvi.events.MviUserIntent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MviPresentation<TUserIntent: MviUserIntent, TUiState: MviUiState> {

    fun processUserIntents(userIntents: Flow<TUserIntent>)

    fun uiStates(): StateFlow<TUiState>
}
