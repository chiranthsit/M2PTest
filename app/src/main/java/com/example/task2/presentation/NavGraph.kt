package com.example.task2.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "ListScreen") {
        composable("ListScreen") {
            val viewModel: PokemonListViewModel = hiltViewModel()
            ListsScreen(viewModel,navController = navController)
        }
        composable("DetailsScreen/{pokemonId}") { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId") ?: ""
            val viewModel: PokemonListViewModel = hiltViewModel()
            DetailsScreen(viewModel,pokemonId = pokemonId,navController = navController)
        }
    }
}

