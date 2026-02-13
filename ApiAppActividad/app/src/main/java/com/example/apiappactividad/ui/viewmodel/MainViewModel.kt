package com.example.apiappactividad.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiappactividad.data.Repository
import com.example.apiappactividad.data.mimgodel.Result
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = Repository()

    // Ara la llista és de tipus 'Result'
    var characterList by mutableStateOf<List<Result>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            isLoading = true
            characterList = repository.getCharactersFromApi()
            isLoading = false
        }
    }
}