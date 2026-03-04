package com.example.apiappactividad.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.collections.filter
import kotlin.text.contains
import kotlin.text.isEmpty
import kotlin.text.isNotEmpty

class SearchBarViewModel : ViewModel() {

    var searchedText by mutableStateOf("")
        private set

    var active by mutableStateOf(false)

    var searchHistory = mutableStateListOf<String>()
        private set

    var filteredNames = mutableStateListOf<String>()
        private set

    fun onSearchTextChange(text: String) {
        searchedText = text
    }

    fun onActiveChange(isActive: Boolean) {
        active = isActive
        if (!isActive) {
             searchedText = ""
            // filteredNames.clear()
        }
    }

    fun onSearch(text: String) {
        if (text.isNotEmpty()) {
            searchHistory.add(text)

            onActiveChange(false)
        }
    }

    fun onClearHistory() {
        searchHistory.clear()
    }
}