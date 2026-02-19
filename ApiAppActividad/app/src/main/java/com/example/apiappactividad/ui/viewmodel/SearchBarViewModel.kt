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

    // 1. ESTAT DEL TEXT (Query)
    var searchedText by mutableStateOf("")
        private set

    // 2. ESTAT DE SI LA BARRA ESTÀ ACTIVA
    var active by mutableStateOf(false)

    // 3. ESTAT DE L'HISTORIAL (El que ja teníem)
    var searchHistory = mutableStateListOf<String>()
        private set

    // 4. NOVES DADES: La "Base de Dades" simulada
    // (Això normalment vindria d'una API o Base de Dades real)

    // 5. RESULTATS FILTRATS: La llista que ensenyarem quan busquem
    var filteredNames = mutableStateListOf<String>()
        private set

    // FUNCIONS (Events)

    fun onSearchTextChange(text: String) {

    }

    fun onActiveChange(isActive: Boolean) {
        active = isActive
        // Opcional: Si tanquem la barra, potser volem netejar la cerca
        if (!isActive) {
            // searchedText = "" (Descomenta si vols que s'esborri en tancar)
            // filteredNames.clear()
        }
    }

    fun onSearch(text: String) {
        if (text.isNotEmpty()) {
            searchHistory.add(text)
            // No tanquem la barra ni esborrem el text immediatament
            // perquè l'usuari vulgui veure el resultat
            onActiveChange(false)
        }
    }

    fun onClearHistory() {
        searchHistory.clear()
    }
}