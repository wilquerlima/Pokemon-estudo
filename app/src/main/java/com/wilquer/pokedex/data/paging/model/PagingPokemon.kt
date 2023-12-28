package com.wilquer.pokedex.data.paging.model

data class PagingPokemon(
    val count: String?,
    val next: String?,
    val previous: String?,
    val results: List<Results>
)

data class Results(
    val name: String?,
    val url: String?
)