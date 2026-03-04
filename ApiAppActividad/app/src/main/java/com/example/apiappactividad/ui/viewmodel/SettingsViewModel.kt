package com.example.apiappactividad.ui.viewmodel

import android.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apiappactividad.data.RecyclerView.SettingsRepository
import kotlin.jvm.java

// --- LA LÒGICA ---
class SettingsViewModel(private val repository: SettingsRepository) : ViewModel() {
    var modeDark by mutableStateOf(repository.obtenerMode())
        private set

    fun actualitzarMode(mode: Boolean) {
        modeDark = mode
        repository.guardarMode(mode)
    }
    fun cambiarMode()
    {
        modeDark = !modeDark
        actualitzarMode(modeDark)
    }
    
    var showMode by mutableStateOf(repository.obtenerShowMode())
        private set
    
    fun actualizarShowMode(showlist: String){
        showMode = showlist
        repository.guardarShowMode(showlist)
    }
    var showGrid by mutableStateOf(repository.obtenerFlagMode())
        private set

    fun actualizarShowGrid(showFLag : Boolean)
    {
        showGrid = showFLag
        repository.guardarFlagMode(showFLag)
    }
    val showlistMode = listOf("List", "Grid")

    var expanded by  mutableStateOf(false)

}

class SettingsViewModelFactory(private val repository: SettingsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        // 1. Comprovem que ens demanen el ViewModel correcte
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {

            // 2. Creem el ViewModel manualment i li "injectem" el repository
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(repository) as T
        }

        // Si ens demanen un altre tipus de viewmodel, donem error
        throw kotlin.IllegalArgumentException("Classe ViewModel desconeguda")
    }
}