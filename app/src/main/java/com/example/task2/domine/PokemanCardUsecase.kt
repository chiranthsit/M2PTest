package com.example.task2.domine

import com.example.task2.data.PokemonRepository
import com.example.task2.domine.entities.PokemanData
import javax.inject.Inject

class PokemanCardUsecase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(): PokemanData {
        val response = repository.getPokemonCards()
        return response
    }
}