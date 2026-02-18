package com.example.apiappactividad.ui.viewmodel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiappactividad.R
import com.example.apiappactividad.data.Repository
import com.example.apiappactividad.data.model.Result
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
    var selectedCharacter: Result? = null

    @Composable
    fun BarItemBtn(icon: Int,label: String,onClick: () -> Unit ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { onClick() }
        ) {
            Icon(
                painter = painterResource(icon),
                tint = colorResource(id = R.color.gray),
                contentDescription = label,
                modifier = Modifier.size(44.dp)
            )
            Text(label, fontSize = 12.sp, color = colorResource(id = R.color.gray))
        }
    }
    fun fetchCharacters() {
        viewModelScope.launch {
            isLoading = true
            characterList = repository.getCharactersFromApi()
            isLoading = false
        }
    }
}

