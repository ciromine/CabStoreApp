package com.example.cabstoreapp.presentation.detail

import com.example.cabstoreapp.core.mvi.events.MviEffect
import com.example.cabstoreapp.domain.model.DomainProduct

sealed class DetailUiEffect : MviEffect {
    data class NavigateToCharacterDetailUiEffect(val domainProduct: DomainProduct) : DetailUiEffect()
}