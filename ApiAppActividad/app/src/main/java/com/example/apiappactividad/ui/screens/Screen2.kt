package com.example.apiappactividad.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.apiappactividad.data.BD.toResult
import com.example.apiappactividad.navigation.Destinations
import com.example.apiappactividad.ui.viewmodel.CharacterViewModel
import com.example.apiappactividad.ui.viewmodel.MainViewModel
import com.example.apiappactividad.ui.viewmodel.ViewDesign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen2(
    navController: NavController,
    viewModel: MainViewModel,
    bdViewModel: CharacterViewModel,
    vDesign: ViewDesign
) {
    val character by bdViewModel.characters.collectAsStateWithLifecycle()

    LazyColumn {
        items(character) { character ->
        if(character.isFavorite) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        viewModel.selectedCharacter = character.toResult()
                        navController.navigate(Destinations.DetailScreen.route)
                    },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Genere: ${character.gender}")
                    Text(text = "Status: ${character.status}")
                }
            }
        }
        }
    }

        /*

        if (!viewModel.showGrid) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(bdViewModel.characters) { character ->
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
                                    .size(50.dp)
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
*/

    }









