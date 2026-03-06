package com.example.apiappactividad.ui.screens


import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage
import com.example.apiappactividad.ui.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen2(
    navController: NavController,
    viewModel: MainViewModel,
    bdViewModel: CharacterViewModel,
    vDesign: ViewDesign,
    reciView: SettingsViewModel
) {
    val character by bdViewModel.characters.collectAsStateWithLifecycle()
    if (reciView.showGrid) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {
        items(character) { character ->
            if (character.isFavorite) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            viewModel.selectedCharacter = character.toResult()
                            navController.navigate(Destinations.DetailScreen.route)
                        },
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = vDesign.colorCard
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = character.name,
                            color = vDesign.colorFont,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Genere: ${character.gender}",color = vDesign.colorFont)
                        Text(text = "Status: ${character.status}",color = vDesign.colorFont)
                    }
                }
            }
        }
    }
    }
    else
    {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize().padding(top = 20.dp)        ) {
            items(character) { character ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { viewModel.selectedCharacter = character.toResult()
                            navController.navigate(Destinations.DetailScreen.route) },
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = vDesign.colorCard
                    )
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
                                maxLines = 2,
                                color = vDesign.colorFont

                                )
                            Text(
                                text = "Age: ${character.age}",
                                color = vDesign.colorFont,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                }}}
    }

    }









