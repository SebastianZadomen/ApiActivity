package com.example.apiappactividad.ui.screens
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.apiappactividad.data.BD.toEntity
import com.example.apiappactividad.ui.viewmodel.CharacterViewModel
import com.example.apiappactividad.ui.viewmodel.MainViewModel
import com.example.apiappactividad.ui.viewmodel.ViewDesign

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: MainViewModel,
    bdViewModel: CharacterViewModel,
    vDesign: ViewDesign
) {

    val character = viewModel.selectedCharacter ?: return
    val extractRoles = viewModel.CleanList(character.roles)
    val extractAlias = viewModel.CleanList(character.alias)
    val favorites by bdViewModel.favorites.collectAsState()

    val isFavorite = favorites.any { it.id == character.id }


    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .padding(top = vDesign.paddingTop, start = vDesign.paddingStart, end = vDesign.paddingEnd),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = character.name,
                    fontSize = vDesign.fontSizeName.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = {
                        if (isFavorite) {
                            bdViewModel.deleteFavorite(character.toEntity())
                        } else {
                            bdViewModel.insertCharacter(
                                character.toEntity().copy(isFavorite = true)
                            )
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorito",
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
            }


            Spacer(modifier = Modifier.height(20.dp))

            AsyncImage(
                model = character.img,
                contentDescription = "imgCharacter",
                modifier = Modifier
                    .size(vDesign.imageSizeDetailScreen)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(20.dp))

        }
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Roles: $extractRoles",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = vDesign.fontSizeDetail.sp
                )
                Text("Alias: $extractAlias",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = vDesign.fontSizeDetail.sp
                )
                Text("Gender: ${character.gender}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = vDesign.fontSizeDetail.sp

                )
                Text("Status: ${character.status}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = vDesign.fontSizeDetail.sp
                )
                Text("Age: ${character.age}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = vDesign.fontSizeDetail.sp
                )

            }
        }
    }

}