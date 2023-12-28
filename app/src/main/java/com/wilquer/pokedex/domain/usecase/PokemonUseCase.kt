package com.wilquer.pokedex.domain.usecase

abstract class PokemonUseCase<out T, in Params> {
    abstract operator fun invoke(params: Params): T
}