package com.wilquer.pokedex.domain.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val name: String?,
    val height: Int?,
    val weight: Int?,
    val id: Int?,
    val sprites: SpritesResponse?,
    val types: SlotType?,
)

data class SpritesResponse(
    val other: Other
)

data class Other(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtWork
)

data class OfficialArtWork(
    @SerializedName("front_default") val frontDefault: String? = null
)

data class SlotType(
    val slot: Int? = null,
    val type: Type
)

data class Type(
    val name: String? = null,
    val url: String? = null
)
