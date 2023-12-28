package com.wilquer.pokedex.ui.details

import androidx.lifecycle.ViewModel
import com.wilquer.pokedex.domain.usecase.PokemonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
internal class DetailsViewModel @Inject constructor(
    private val useCase: PokemonDetailsUseCase
): ViewModel() {

}