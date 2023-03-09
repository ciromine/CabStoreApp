package com.example.cabstoreapp.presentation.detail

import com.example.cabstoreapp.core.execution.CoroutineExecutionThread
import com.example.cabstoreapp.domain.GetProductListUseCase
import com.example.cabstoreapp.domain.SaveProductDatabaseUseCase
import com.example.cabstoreapp.domain.model.DomainProduct
import com.example.cabstoreapp.presentation.productlist.ProductListAction
import com.example.cabstoreapp.presentation.productlist.ProductListResult
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DetailProcessor @Inject constructor(
    private val useCase: SaveProductDatabaseUseCase,
    private val coroutineThreadProvider: CoroutineExecutionThread
) {

    fun actionProcessor(actions: ProductListAction): Flow<ProductListResult> =
        when (actions) {
            is ProductListAction.GetProductListAction -> saveProductActionProcessor()
            is ProductListAction.GoToDetailAction -> goToDetailActionProcessor(actions.domainProduct)
        }

    private fun saveProductActionProcessor(): Flow<ProductListResult> =
        useCase.execute()
            .map {
                ProductListResult.GetProductListResult.Success(it.products) as ProductListResult
            }.onStart {
                emit(ProductListResult.GetProductListResult.InProgress)
            }
            .flowOn(coroutineThreadProvider.ioThread())

    private fun goToDetailActionProcessor(domainProduct: DomainProduct): Flow<ProductListResult> = flow {
        emit(ProductListResult.NavigateToResult.GoToDetail(domainProduct))
    }
}
