package com.example.apiappactividad.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiappactividad.data.BD.*
import com.example.apiappactividad.data.model.Result
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CharacterViewModel(private val dao: CharacterDao) : ViewModel() {

    // Todos los personajes
    var selectedCharacterBd : CharacterEntity? = null
    val characters: StateFlow<List<CharacterEntity>> =
        dao.getAllCharacters()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    val favorites: StateFlow<List<CharacterEntity>> =
        dao.getFavorites()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    fun insertCharacter(character: CharacterEntity) = viewModelScope.launch {
        dao.insertCharacter(character)
    }

    fun toggleFavorite(character: CharacterEntity) = viewModelScope.launch {
        dao.updateCharacter(
            character.copy(isFavorite = true)
        )
    }
    fun upsertFavorite(character: CharacterEntity) = viewModelScope.launch {
        dao.insertCharacter(
            character.copy(isFavorite = !character.isFavorite)
        )
    }
    fun deleteFavorite(character: CharacterEntity) =  viewModelScope.launch {
        dao.deleteCharacter(
            character.copy()
        )
    }
    fun deleteAllFavorites() = viewModelScope.launch {
        dao.deleteAllFavorites()
    }
}