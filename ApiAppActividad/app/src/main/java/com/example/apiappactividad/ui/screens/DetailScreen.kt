package com.example.apiappactividad.ui.screens
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
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
        })

    }
}