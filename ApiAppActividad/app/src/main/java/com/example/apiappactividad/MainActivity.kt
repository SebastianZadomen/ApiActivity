package com.example.apiappactividad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apiappactividad.ui.theme.ApiAppActividadTheme
import com.example.apiappactividad.Routes
import com.example.apiappactividad.view.Screen1
import com.example.apiappactividad.view.Screen2
import com.example.apiappactividad.view.Screen3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiAppActividadTheme {
                val navigationController = rememberNavController()
                NavHost(
                    navController = navigationController,
                    startDestination = Routes.Pantalla1.route
                ) {
                    composable(Routes.Pantalla1.route) { Screen1(navigationController) }
                    composable(Routes.Pantalla2.route) { Screen2(navigationController) }
                    composable(Routes.Pantalla3.route) { Screen3(navigationController) }
                }


            }
        }
    }
}

