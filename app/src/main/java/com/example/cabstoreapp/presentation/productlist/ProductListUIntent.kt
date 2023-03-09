package com.example.cabstoreapp.presentation.productlist

import com.example.cabstoreapp.core.mvi.events.MviUserIntent
import com.example.cabstoreapp.domain.model.DomainProduct

sealed class ProductListUIntent : MviUserIntent {

    object InitialUIntent : ProductListUIntent()

    object RetrySeeCharacterListUIntent : ProductListUIntent()

    data class SeeDetailUIntent(val domainProduct: DomainProduct) : ProductListUIntent()
}

