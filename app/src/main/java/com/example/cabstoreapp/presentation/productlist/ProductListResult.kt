package com.example.cabstoreapp.presentation.productlist

import com.example.cabstoreapp.core.mvi.events.MviResult
import com.example.cabstoreapp.domain.model.DomainProduct

sealed class ProductListResult : MviResult {

    sealed class GetProductListResult : ProductListResult() {
        object InProgress : ProductListResult()
        data class Success(val results: List<DomainProduct>) : ProductListResult()
        object Error : ProductListResult()
    }

    sealed class NavigateToResult : ProductListResult() {
        data class GoToDetail(val domainRecipe: DomainProduct) : NavigateToResult()
    }
}
