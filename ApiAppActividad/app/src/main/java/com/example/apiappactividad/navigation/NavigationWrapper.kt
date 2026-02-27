package com.example.apiappactividad.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.apiappactividad.ui.screens.DetailScreen
import com.example.apiappactividad.ui.screens.Screen1
import com.example.apiappactividad.ui.screens.Screen2
import com.example.apiappactividad.ui.screens.Screen3
import com.example.apiappactividad.ui.viewmodel.CharacterViewModel
import com.example.apiappactividad.ui.viewmodel.MainViewModel
import com.example.apiappactividad.ui.viewmodel.SearchBarViewModel
import com.example.apiappactividad.ui.viewmodel.ViewDesign

@Composable
fun NavigationWrapper(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    bdViewModel: CharacterViewModel,
    viewDesignResponsi: ViewDesign
) {

    val searchViewModel: SearchBarViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Destinations.Screen1.route
    ) {

        composable(Destinations.Screen1.route) {
            Screen1(navController, mainViewModel, searchViewModel,viewDesignResponsi)
        }

        composable(Destinations.Screen2.route) {
            Screen2(navController, mainViewModel, bdViewModel,viewDesignResponsi)
        }

        composable(Destinations.Screen3.route) {
            Screen3(navController, mainViewModel,viewDesignResponsi)
        }

        composable(Destinations.DetailScreen.route) {
            DetailScreen(navController, mainViewModel, bdViewModel,viewDesignResponsi)
        }
    }
}