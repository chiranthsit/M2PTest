package com.example.task2.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.example.task2.domine.entities.PokemanData
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.task2.domine.entities.Data

@Composable
fun ListsScreen(viewModel: PokemonListViewModel, navController: NavController) {
    //val viewModel: PokemonListViewModel = viewModel()
    val pokemonList by viewModel.pokemonList.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(40.dp)) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            BasicTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    viewModel.searchPokemon(it)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .border(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                            .padding(8.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (searchQuery.isEmpty()) {
                            Text("Search Pokémon")
                        }
                        innerTextField()
                    }
                }
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.sortByLevel() }) {
                Text(text ="Sort by Level",)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.sortByHp() }) {
                Text("Sort by HP")
            }
        }
        LazyColumn {
            items(pokemonList.size) { index ->
                val pokemon = pokemonList[index]
                PokemonCardItem(pokemon) {
                    navController.navigate("DetailsScreen/${pokemon.id}")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun PokemonCardItem(pokemon: Data, onClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(model = pokemon .images.small, contentDescription = null, modifier = Modifier.size(100.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = pokemon.name)
                Text(text = "Types: ${pokemon.types.joinToString(", ")}")
                Text(text = "Level: ${pokemon.level}")
                Text(text = "HP: ${pokemon.hp}")
            }
        }
    }
}