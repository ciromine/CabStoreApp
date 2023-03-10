package com.example.cabstoreapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cabstoreapp.core.mvi.MviPresentation
import com.example.cabstoreapp.core.mvi.MviPresentationEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val reducer: DetailReducer,
    private val actionProcessorHolder: DetailProcessor
) : ViewModel(), MviPresentation<DetailUIntent, DetailUiState>,
    MviPresentationEffect<DetailUiEffect> {

    private val defaultUiState: DetailUiState = DetailUiState.DefaultUiState

    private val uiState = MutableStateFlow(defaultUiState)
    private val uiEffect: MutableSharedFlow<DetailUiEffect> = MutableSharedFlow()

    override fun processUserIntents(userIntents: Flow<DetailUIntent>) {
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

    private fun DetailUIntent.toAction(): DetailAction {
        return when (this) {
            DetailUIntent.InitialUIntent, DetailUIntent.RetrySeeCharacterListUIntent -> DetailAction.BuyProductAction
            is DetailUIntent.SeeDetailUIntent -> DetailAction.GoToDetailAction(
                domainProduct
            )
        }
    }

    private fun Flow<DetailResult>.handleEffect(): Flow<DetailResult> {
        return onEach { change ->
            val event = when (change) {
                is DetailResult.NavigateToResult.GoToDetail -> DetailUiEffect.NavigateToCharacterDetailUiEffect(
                    change.domainRecipe
                )
                else -> return@onEach
            }
            uiEffect.emit(event)
        }
    }

    override fun uiStates(): StateFlow<DetailUiState> = uiState
    override fun uiEffect(): SharedFlow<DetailUiEffect> = uiEffect.asSharedFlow()
}
