package com.example.task2.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.task2.domine.entities.Data


@Composable
fun DetailsScreen(viewModel: PokemonListViewModel,pokemonId: String, navController: NavController) {

    val pokemonList by viewModel.pokemonList.observeAsState(emptyList())
    var pokemon by remember { mutableStateOf<Data?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    if (pokemonList.isNotEmpty()) {
        pokemon = pokemonList.find { it.id == pokemonId }
        isLoading = false
    }

    if (isLoading) {
        Text(text = "Loading...", modifier = Modifier.padding(100.dp))
    } else if (pokemon != null) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = pokemon!!.images.small,
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Name: ${pokemon?.name}")
            Text(text = "Types: ${pokemon?.types?.joinToString(", ")}")
            Text(text = "Level: ${pokemon?.level ?: "N/A"}")
            Text(text = "HP: ${pokemon?.hp ?: "N/A"}")
            Text(text = "attacks: ${pokemon?.attacks ?: "N/A"}", maxLines = 2)
            Text(text = "weaknesses: ${pokemon?.weaknesses ?: "N/A"}",maxLines = 2)
            Text(text = "abilities: ${pokemon?.abilities ?: "N/A"}",maxLines = 2)
            Text(text = "resistances: ${pokemon?.resistances ?: "N/A"}",maxLines = 2)

        }
    } else {
        Text(text = "Pokémon not found.", modifier = Modifier.padding(100.dp))
    }
}
