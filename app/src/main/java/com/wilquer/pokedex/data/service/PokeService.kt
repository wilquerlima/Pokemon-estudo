package com.wilquer.pokedex.data.service

import com.wilquer.pokedex.data.paging.model.PagingPokemon
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokeService {

    @GET("pokemon")
    suspend fun getPokemonListApi(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): PagingPokemon

    @GET("pokemon/{id}")
    suspend fun getPokemonDetailsApi(
        @Path("id") id: String
    )
}