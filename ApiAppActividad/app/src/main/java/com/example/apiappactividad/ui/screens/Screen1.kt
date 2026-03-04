package com.example.apiappactividad.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip


import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

import com.example.apiappactividad.navigation.Destinations
import com.example.apiappactividad.ui.viewmodel.MainViewModel
import com.example.apiappactividad.ui.viewmodel.SearchBarViewModel
import com.example.apiappactividad.ui.viewmodel.SettingsViewModel
import com.example.apiappactividad.ui.viewmodel.ViewDesign


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1(
    navController: NavController,
    viewModel: MainViewModel,
    searchViewModel: SearchBarViewModel,
    vDesign: ViewDesign,
    reciView: SettingsViewModel,
) {
    val query = searchViewModel.searchedText

    val filteredList = viewModel.characterList.filter { result ->
        result.name
            .lowercase()
            .contains(query.lowercase().trim())
    }

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {

        SearchBar(
            query = query,
            modifier = Modifier.padding(8.dp),
            onQueryChange = { searchViewModel.onSearchTextChange(it) },
            onSearch = { searchViewModel.onSearch(it) },
            active = searchViewModel.active,
            onActiveChange = { searchViewModel.onActiveChange(it) },
            placeholder = { Text("Search character...") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "iconSearch")
            },
            trailingIcon = {

                    Icon(
                        Icons.Default.Close,
                        contentDescription = "closeBar",
                        modifier = Modifier.clickable {
                            searchViewModel.onSearchTextChange("")
                            searchViewModel.active = false
                        }
                    )
                }

        ) {

            if (query.isEmpty()) {
                LazyColumn {
                    items(searchViewModel.searchHistory) { item ->

                        ListItem(
                            headlineContent = { Text(item) },
                            modifier = Modifier.clickable {

                                searchViewModel.onSearchTextChange(item)

                            }
                        )
                    }
                }

            } else {

                LazyColumn {
                    items(filteredList) { character ->

                        ListItem(
                            headlineContent = { Text(character.name) },
                            modifier = Modifier.clickable {
                                viewModel.selectedCharacter = character
                                navController.navigate(Destinations.DetailScreen.route)
                                searchViewModel.onActiveChange(false)
                                searchViewModel.searchHistory.add(query)
                            }
                        )
                    }
                }
            }
        }


        if (viewModel.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            if (!reciView.showGrid) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(vDesign.gridCells),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(filteredList) { character ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable { viewModel.selectedCharacter = character
                                    navController.navigate(Destinations.DetailScreen.route) },
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = character?.img,
                                    contentDescription = "Imatge character",
                                    modifier = Modifier
                                        .size(vDesign.imageSizeScreen1)
                                        .clip(RoundedCornerShape(16.dp))

                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Column {
                                    Text(
                                        text = character.name,
                                        style = MaterialTheme.typography.titleLarge,
                                        maxLines = 2
                                    )
                                    Text(
                                        text = "Age: ${character.age}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }

                        }}}
            }
            else{
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(filteredList) { character ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    viewModel.selectedCharacter = character
                                    navController.navigate(Destinations.DetailScreen.route)
                                },
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {

                                val extract = viewModel.CleanList(character.roles)

                                Text(
                                    text = character.name,
                                    style = MaterialTheme.typography.headlineSmall
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "Genere: ${character.gender}")
                                Text(text = "Roles: $extract")
                                Text(text = "Status: ${character.status}")
                            }
                        }
                    }
                }
            }
        }
    }
}



