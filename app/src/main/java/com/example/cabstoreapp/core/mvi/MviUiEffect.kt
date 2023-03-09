package com.example.cabstoreapp.core.mvi

import com.example.cabstoreapp.core.mvi.events.MviEffect

interface MviUiEffect<in TUiEffect : MviEffect> {
    fun handleEffect(uiEffect: TUiEffect)
}
