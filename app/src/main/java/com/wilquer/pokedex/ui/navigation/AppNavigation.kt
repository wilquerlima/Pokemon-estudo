package com.wilquer.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wilquer.pokedex.ui.home.PokedexHome
import com.wilquer.pokedex.ui.navigation.Navigation.Args.POKEMON_ID

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation.Routes.HOME) {
        composable(route = Navigation.Routes.HOME) {
            PokedexHome()
        }
        composable(
            route = Navigation.Routes.DETAILS,
            arguments = listOf(navArgument(name = POKEMON_ID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val userId =
                requireNotNull(backStackEntry.arguments?.getString(POKEMON_ID)) { "User id is required as an argument" }
        }
    }
}

object Navigation {
    object Args {
        const val POKEMON_ID = "pokemon_id"
    }

    object Routes {
        const val HOME = "home"
        const val DETAILS = "details"
    }
}