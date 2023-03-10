package com.example.cabstoreapp.presentation.detail

import com.example.cabstoreapp.core.mvi.events.MviUserIntent
import com.example.cabstoreapp.domain.model.DomainProduct

sealed class DetailUIntent : MviUserIntent {

    object InitialUIntent : DetailUIntent()

    object RetrySeeCharacterListUIntent : DetailUIntent()

    data class SeeDetailUIntent(val domainProduct: DomainProduct) : DetailUIntent()
}