package com.example.apiappactividad.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.apiappactividad.R

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.Alignment


import androidx.compose.ui.unit.dp
import com.example.apiappactividad.Routes
import com.example.apiappactividad.data.model.Result
import com.example.apiappactividad.ui.viewmodel.MainViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1(navController: NavController, viewModel: MainViewModel, ) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("API List")
                }
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary, ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    viewModel.BarItemBtn(
                        icon = R.drawable.outline_av_timer_24,
                        label = "Home"
                    ) { }

                    viewModel.BarItemBtn(
                        icon = R.drawable.outline_bookmark_heart_24,
                        label = "Favorites"
                    ) {
                        navController.navigate(Routes.Pantalla2.route)
                    }

                    viewModel.BarItemBtn(
                        icon = R.drawable.outline_construction_24,
                        label = "Settings"
                    ) {
                        navController.navigate(Routes.Pantalla3.route)
                    }
                }
            }
        },

        ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            if (viewModel.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn {
                    // Iterem sobre objectes de tipus 'Result'
                    items(viewModel.characterList) { character ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(8.dp).clickable{
                                viewModel.selectedCharacter = character
                                navController.navigate(Routes.DetailScreen.route)},
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

