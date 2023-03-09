package com.example.cabstoreapp.presentation.productlist

import com.example.cabstoreapp.core.mvi.events.MviAction
import com.example.cabstoreapp.domain.model.DomainProduct

sealed class ProductListAction : MviAction {

    object GetProductListAction : ProductListAction()

    data class GoToDetailAction(val domainProduct: DomainProduct) : ProductListAction()
}
