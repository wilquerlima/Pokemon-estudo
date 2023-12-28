package com.wilquer.pokedex.domain.usecase

import com.wilquer.pokedex.data.repository.PokemonRepository
import javax.inject.Inject

internal class PokemonDetailsUseCase @Inject constructor(
    private val repository: PokemonRepository
): PokemonUseCase<String, String>() {
    override fun invoke(params: String): String {
        TODO("Not yet implemented")
    }
}