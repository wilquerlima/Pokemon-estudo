package com.wilquer.pokedex.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wilquer.pokedex.data.paging.model.Results
import com.wilquer.pokedex.data.repository.PokemonRepository
import retrofit2.HttpException
import java.io.IOException

internal class PokemonPagingSource(
    private val repository: PokemonRepository
) : PagingSource<Int, Results>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val currentPage = params.key ?: 1
            val pokemonPaging = repository.getPokemonList(
                params = params,
                offset = if (currentPage == 1) 0 else params.loadSize * currentPage
            )

            LoadResult.Page(
                data = pokemonPaging.results,
                prevKey = null,
                nextKey = if (pokemonPaging.next.isNullOrEmpty()) null else currentPage.inc()
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}