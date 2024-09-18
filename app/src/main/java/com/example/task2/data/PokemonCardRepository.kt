package com.example.task2.data

import com.example.task2.data.PokemonService
import com.example.task2.domine.entities.PokemanData
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val apiService: PokemonService) {
    suspend fun getPokemonCards(): PokemanData {
        return apiService.getPokemonCards()
    }
}