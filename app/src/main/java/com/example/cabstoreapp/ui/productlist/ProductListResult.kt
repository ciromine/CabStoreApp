package com.example.cabstoreapp.ui.productlist

import com.example.cabstoreapp.domain.model.DomainProduct

sealed class ProductListResult {

    sealed class GetProductListResult : ProductListResult() {
        object InProgress : ProductListResult()
        data class Success(val results: List<DomainProduct>) : ProductListResult()
        object Error : ProductListResult()
    }
}