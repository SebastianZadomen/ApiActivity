package com.example.apiappactividad.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment


import androidx.compose.ui.unit.dp

import com.example.apiappactividad.navigation.Destinations
import com.example.apiappactividad.ui.viewmodel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1(
    navController: NavController,
    viewModel: MainViewModel,
) {

        Box(modifier = Modifier.fillMaxSize()) {

            if (viewModel.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn {
                    // Iterem sobre objectes de tipus 'Result'
                    items(viewModel.characterList) { character ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(8.dp).clickable{
                                viewModel.selectedCharacter = character
                                navController.navigate(Destinations.DetailScreen.route)},
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                // Usem les variables que t'ha creat el plugin
                                val extract = CleanList(character.roles)
                                Text(
                                    text = character.name,
                                    style = MaterialTheme.typography.headlineSmall
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "Gènere: ${character.gender}")
                                Text(text = "Roles: ${extract}")
                                // Fixa't que el plugin ha posat 'birth_year' amb guió baix
                                Text(text = "Status: ${character.status}")
                            }
                        }
                    }
                }
            }
        }
    }





fun CleanList(listString : List<String>): String{

    var palabra : String = ""
    for (item in listString) {
        if(listString[listString.size-1] == item)
        {
            palabra +=item
        }
        else
        {
            palabra="$item, "
        }
    }
    if (listString.isEmpty())
    {
        palabra = "None"
    }
    return palabra
}

