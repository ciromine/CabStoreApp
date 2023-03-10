package com.example.cabstoreapp.presentation.detail

import com.example.cabstoreapp.core.mvi.events.MviResult
import com.example.cabstoreapp.domain.model.DomainProduct

sealed class DetailResult : MviResult {

    sealed class GetDetailResult : DetailResult() {
        object InProgress : DetailResult()
        data class Success(val results: List<DomainProduct>) : DetailResult()
        object Error : DetailResult()
    }

    sealed class NavigateToResult : DetailResult() {
        data class GoToDetail(val domainRecipe: DomainProduct) : NavigateToResult()
    }
}