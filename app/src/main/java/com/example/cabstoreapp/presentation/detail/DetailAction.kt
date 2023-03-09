package com.example.cabstoreapp.presentation.detail

import com.example.cabstoreapp.core.mvi.events.MviAction
import com.example.cabstoreapp.domain.model.DomainProduct

sealed class DetailAction : MviAction {

    object BuyProductAction : DetailAction()

    data class GoToDetailAction(val domainProduct: DomainProduct) : DetailAction()
}
