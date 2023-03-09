package com.example.cabstoreapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cabstoreapp.core.mvi.MviPresentation
import com.example.cabstoreapp.core.mvi.MviPresentationEffect
import com.example.cabstoreapp.presentation.productlist.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val reducer: ProductListReducer,
    private val actionProcessorHolder: ProductListProcessor
) : ViewModel(), MviPresentation<ProductListUIntent, ProductListUiState>,
    MviPresentationEffect<ProductListUiEffect> {

    private val defaultUiState: ProductListUiState = ProductListUiState.DefaultUiState

    private val uiState = MutableStateFlow(defaultUiState)
    private val uiEffect: MutableSharedFlow<ProductListUiEffect> = MutableSharedFlow()

    override fun processUserIntents(userIntents: Flow<ProductListUIntent>) {
        userIntents
            .buffer()
            .flatMapMerge { userIntent ->
                actionProcessorHolder.actionProcessor(userIntent.toAction())
            }
            .handleEffect()
            .scan(defaultUiState) { previousUiState, result ->
                with(reducer) { previousUiState reduce result }
            }
            .onEach { uiState ->
                this.uiState.value = uiState
            }
            .launchIn(viewModelScope)
    }

    private fun ProductListUIntent.toAction(): ProductListAction {
        return when (this) {
            ProductListUIntent.InitialUIntent, ProductListUIntent.RetrySeeCharacterListUIntent -> ProductListAction.GetProductListAction
            is ProductListUIntent.SeeDetailUIntent -> ProductListAction.GoToDetailAction(
                domainProduct
            )
        }
    }

    private fun Flow<ProductListResult>.handleEffect(): Flow<ProductListResult> {
        return onEach { change ->
            val event = when (change) {
                is ProductListResult.NavigateToResult.GoToDetail -> ProductListUiEffect.NavigateToCharacterDetailUiEffect(
                    change.domainRecipe
                )
                else -> return@onEach
            }
            uiEffect.emit(event)
        }
    }

    override fun uiStates(): StateFlow<ProductListUiState> = uiState
    override fun uiEffect(): SharedFlow<ProductListUiEffect> = uiEffect.asSharedFlow()
}
