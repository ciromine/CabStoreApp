package com.example.cabstoreapp.core.mvi

import com.example.cabstoreapp.core.mvi.events.MviEffect
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharedFlow

@FlowPreview
@ExperimentalCoroutinesApi
interface MviPresentationEffect<TUiEffect : MviEffect> {
    fun uiEffect(): SharedFlow<TUiEffect>
}
