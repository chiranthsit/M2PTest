package com.example.task2.data

import com.example.task2.domine.entities.PokemanData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PokemonService {
    @GET("/v2/cards?pageSize=20")
    suspend fun getPokemonCards(): PokemanData
}