package com.wilquer.pokedex.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wilquer.pokedex.data.paging.model.Results
import com.wilquer.pokedex.domain.usecase.PokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val useCase: PokemonListUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<PagingData<Results>>(PagingData.empty())
    val viewState: Flow<PagingData<Results>> = _viewState

    private fun getPokemonList() = viewModelScope.launch {
        useCase(Unit).flow
            .cachedIn(viewModelScope)
            .collect {
                _viewState.update { it }
            }
    }

    fun getPokemon(): Flow<PagingData<Results>> = useCase(Unit).flow.cachedIn(viewModelScope)

    fun handleIntent(intent: HomeViewIntent) {
        when (intent) {
            HomeViewIntent.Retry -> {}
            HomeViewIntent.Load -> {getPokemonList()}
        }
    }
}

sealed interface HomeViewState {
    object Loading : HomeViewState
    object Error : HomeViewState
    object Success : HomeViewState
}

sealed interface HomeViewIntent {
    object Retry : HomeViewIntent
    object Load : HomeViewIntent
}

interface HomeViewEffect {

}