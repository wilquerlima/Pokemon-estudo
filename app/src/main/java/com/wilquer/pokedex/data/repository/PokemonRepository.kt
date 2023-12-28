package com.wilquer.pokedex.data.repository

import androidx.paging.PagingSource
import com.wilquer.pokedex.data.paging.model.PagingPokemon

internal interface PokemonRepository {
    suspend fun getPokemonList(params: PagingSource.LoadParams<Int>, offset: Int): PagingPokemon
    suspend fun getPokemonDetail()
}