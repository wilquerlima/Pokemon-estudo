package com.wilquer.pokedex.data.repository

import androidx.paging.PagingSource
import com.wilquer.pokedex.data.paging.model.PagingPokemon
import com.wilquer.pokedex.data.service.PokeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class PokemonRepositoryImpl @Inject constructor(
    private val service: PokeService
) : PokemonRepository {

    override suspend fun getPokemonList(
        params: PagingSource.LoadParams<Int>,
        offset: Int
    ): PagingPokemon = withContext(Dispatchers.IO) {
        return@withContext service.getPokemonListApi(limit = params.loadSize, offset = offset)
    }

    override suspend fun getPokemonDetail() {
        service.getPokemonDetailsApi(id = "")
    }
}