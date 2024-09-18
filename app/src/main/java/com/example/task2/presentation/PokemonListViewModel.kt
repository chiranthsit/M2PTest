package com.example.task2.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task2.domine.PokemanCardUsecase
import com.example.task2.domine.entities.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val usecase: PokemanCardUsecase) : ViewModel() {

    private val _pokemonList = MutableLiveData<List<Data>>(emptyList())
    val pokemonList: LiveData<List<Data>> get() = _pokemonList

    private val _searchQuery = MutableLiveData<String>("")
    val searchQuery: LiveData<String> get() = _searchQuery

    init {
        fetchPokemonCards()
    }

    fun fetchPokemonCards() {
        viewModelScope.launch {
            _pokemonList.value = usecase.invoke().data
        }
    }

    fun searchPokemon(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            _pokemonList.value = usecase.invoke().data.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }

    fun sortByLevel() {
        _pokemonList.value = _pokemonList.value?.sortedBy { it.level?.toIntOrNull() ?: 0 }
    }

    fun sortByHp() {
        _pokemonList.value = _pokemonList.value?.sortedBy { it.hp?.toIntOrNull() ?: 0 }
    }
}