package com.wilquer.pokedex.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.wilquer.pokedex.data.paging.model.Results

@Composable
internal fun PokedexHome(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val stateItems: LazyPagingItems<Results> = viewModel.getPokemon().collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(stateItems.itemCount) {
            Text(text = stateItems[it]?.name.orEmpty())
        }
        stateItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { Text(text = "Loading") }
                }

                loadState.append is LoadState.Loading -> {
                    //You can add modifier to manage load state when next response page is loading
                }

                loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                    item { Text(text = "Error") }
                }

            }
//            item {
//                Button(
//                    onClick = { viewModel.handleIntent(HomeViewIntent.Load) },
//                    modifier = Modifier.wrapContentSize()
//                ) {
//                    Text(text = "click")
//                }
//            }
        }
    }


}