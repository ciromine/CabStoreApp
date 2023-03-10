package com.example.cabstoreapp.presentation.productlist

import com.example.cabstoreapp.core.mvi.events.MviEffect
import com.example.cabstoreapp.domain.model.DomainProduct

sealed class ProductListUiEffect : MviEffect {
    data class NavigateToProductDetailUiEffect(val domainProduct: DomainProduct) : ProductListUiEffect()
}

