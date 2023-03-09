package com.example.cabstoreapp.presentation.productlist

import com.example.cabstoreapp.core.execution.CoroutineExecutionThread
import com.example.cabstoreapp.domain.GetProductListUseCase
import com.example.cabstoreapp.domain.model.DomainProduct
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ProductListProcessor @Inject constructor(
    private val useCase: GetProductListUseCase,
    private val coroutineThreadProvider: CoroutineExecutionThread
) {

    fun actionProcessor(actions: ProductListAction): Flow<ProductListResult> =
        when (actions) {
            is ProductListAction.GetProductListAction -> getProductListActionProcessor()
            is ProductListAction.GoToDetailAction -> goToDetailActionProcessor(actions.domainProduct)
        }

    private fun getProductListActionProcessor(): Flow<ProductListResult> =
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

