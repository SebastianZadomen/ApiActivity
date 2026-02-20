package com.example.apiappactividad.ui.screens
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.apiappactividad.ui.viewmodel.MainViewModel

@Composable
fun DetailScreen(navController: NavController, viewModel: MainViewModel) {
    ConstraintLayout(Modifier.fillMaxSize()){
        val character = viewModel.selectedCharacter

        val(img , titol, imfo , heart) = createRefs()

        Text(character?.name ?: "", fontSize = 20.sp,modifier = Modifier.constrainAs(titol)
        {
            top.linkTo(parent.top, margin = 50.dp)
            centerHorizontallyTo(parent)
        },
            fontWeight = FontWeight.Bold)
        AsyncImage(
            model = character?.img,
            contentDescription = "imgCharacter",
            modifier = Modifier.constrainAs(img){
                top.linkTo(titol.top, margin = 50.dp)
                centerHorizontallyTo(parent)
            }.width(350.dp).height(400.dp),
            contentScale = ContentScale.Crop

        )
    }
}