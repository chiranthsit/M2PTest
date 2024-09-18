package com.example.task2.data

import com.example.task2.domine.PokemanCardUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)  // Set connection timeout
            .readTimeout(30, TimeUnit.SECONDS)     // Set read timeout
            .writeTimeout(30, TimeUnit.SECONDS)    // Set write timeout
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.pokemontcg.io/")
            .client(okHttpClient)                  // Use the custom OkHttpClient
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    fun providePokemonApiService(retrofit: Retrofit): PokemonService {
        return retrofit.create(PokemonService::class.java)
    }

    @Provides
    fun providePokemonRepository(apiService: PokemonService): PokemonRepository {
        return PokemonRepository(apiService)
    }


    @Provides
    fun provideFetchPokemonCardsUseCase(repository: PokemonRepository): PokemanCardUsecase {
        return PokemanCardUsecase(repository)
    }
}