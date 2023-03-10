package com.example.cabstoreapp.presentation.detail

import com.example.cabstoreapp.core.execution.CoroutineExecutionThread
import com.example.cabstoreapp.domain.SaveProductDatabaseUseCase
import com.example.cabstoreapp.domain.model.DomainProduct
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DetailProcessor @Inject constructor(
    private val useCase: SaveProductDatabaseUseCase,
    private val coroutineThreadProvider: CoroutineExecutionThread
) {

    fun actionProcessor(actions: DetailAction): Flow<DetailResult> =
        when (actions) {
            is DetailAction.BuyProductAction -> saveProductActionProcessor()
            is DetailAction.GoToDetailAction -> goToDetailActionProcessor(actions.domainProduct)
        }

    private fun saveProductActionProcessor(): Flow<DetailResult> =
        useCase.execute()
            .map {
                DetailResult.GetDetailResult.Success(it.products) as DetailResult
            }.onStart {
                emit(DetailResult.GetDetailResult.InProgress)
            }
            .flowOn(coroutineThreadProvider.ioThread())

    private fun goToDetailActionProcessor(domainProduct: DomainProduct): Flow<DetailResult> = flow {
        emit(DetailResult.NavigateToResult.GoToDetail(domainProduct))
    }
}
