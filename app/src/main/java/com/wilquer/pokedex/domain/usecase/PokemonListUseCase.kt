package com.wilquer.pokedex.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wilquer.pokedex.data.paging.PokemonPagingSource
import com.wilquer.pokedex.data.paging.model.Results
import com.wilquer.pokedex.data.repository.PokemonRepository
import javax.inject.Inject

internal class PokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) : PokemonUseCase<Pager<Int, Results>, Unit>() {

    override fun invoke(params: Unit): Pager<Int, Results> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                initialLoadSize = INITIAL_LOAD_SIZE,
            ),
            initialKey = 1,
            pagingSourceFactory = {
                PokemonPagingSource(repository)
            }
        )
    }

    companion object {
        private const val PAGE_SIZE = 150
        private const val INITIAL_LOAD_SIZE = 150
        private const val PREFETCH_DISTANCE = 5
    }
}