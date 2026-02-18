package com.example.apiappactividad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apiappactividad.data.model.Result
import com.example.apiappactividad.ui.screens.DetailScreen
import com.example.apiappactividad.ui.theme.ApiAppActividadTheme
import com.example.apiappactividad.ui.screens.Screen1
import com.example.apiappactividad.ui.screens.Screen2
import com.example.apiappactividad.ui.screens.Screen3
import com.example.apiappactividad.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiAppActividadTheme {
                val viewModel: MainViewModel = viewModel()
                val navigationController = rememberNavController()
                NavHost(
                    navController = navigationController,
                    startDestination = Routes.Pantalla1.route
                ) {
                    composable(Routes.Pantalla1.route) {
                        Screen1(navigationController, viewModel)
                    }

                    composable(Routes.Pantalla2.route) {
                        Screen2(navigationController, viewModel)
                    }

                    composable(Routes.Pantalla3.route) {
                        Screen3(navigationController, viewModel)
                    }

                    composable(Routes.DetailScreen.route) {
                        DetailScreen(navigationController, viewModel)
                    }
                }


            }
        }
    }
}

