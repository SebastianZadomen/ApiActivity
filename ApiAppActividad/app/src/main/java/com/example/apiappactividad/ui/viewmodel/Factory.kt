package com.example.apiappactividad.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apiappactividad.data.BD.CharacterDao

class CharacterViewModelFactory(
    private val dao: CharacterDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}