    package com.example.apiappactividad

    import android.annotation.SuppressLint
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.activity.viewModels
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.padding
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Favorite
    import androidx.compose.material.icons.filled.Home
    import androidx.compose.material.icons.filled.Person
    import androidx.compose.material.icons.filled.Settings
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Icon
    import androidx.compose.material3.NavigationBar
    import androidx.compose.material3.NavigationBarItem
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableIntStateOf
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.res.colorResource
    import androidx.lifecycle.viewmodel.compose.viewModel
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import com.example.apiappactividad.data.BD.AppDatabase
    import com.example.apiappactividad.data.model.Result
    import com.example.apiappactividad.navigation.Destinations
    import com.example.apiappactividad.ui.screens.DetailScreen
    import com.example.apiappactividad.ui.theme.ApiAppActividadTheme
    import com.example.apiappactividad.ui.screens.Screen1
    import com.example.apiappactividad.ui.screens.Screen2
    import com.example.apiappactividad.ui.screens.Screen3
    import com.example.apiappactividad.ui.viewmodel.MainViewModel
    import com.example.apiappactividad.navigation.NavigationItem
    import com.example.apiappactividad.navigation.NavigationWrapper
    import com.example.apiappactividad.ui.viewmodel.CharacterViewModel
    import com.example.apiappactividad.ui.viewmodel.CharacterViewModelFactory
    import com.example.apiappactividad.ui.viewmodel.SearchBarViewModel
    import kotlin.getValue


    class MainActivity : ComponentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                ApiAppActividadTheme {
                    MyApp()


                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyApp() {
        var selectedItem by remember { mutableIntStateOf(0) }
        var color by remember { mutableStateOf(Color.White)  }

        val navController = rememberNavController()
        val mainViewModel: MainViewModel = viewModel()
        val context = LocalContext.current
        val database = AppDatabase.getDatabase(context)
        val dao = database.characterDao()

        val factory = CharacterViewModelFactory(dao)

        val bdViewModel: CharacterViewModel = viewModel(factory = factory)

        val items = listOf(

            NavigationItem("Home", Icons.Default.Home, Destinations.Screen1, 1),
            NavigationItem("Favorites", Icons.Default.Favorite, Destinations.Screen2, 2),
            NavigationItem("Settings", Icons.Default.Settings, Destinations.Screen3, 3)
        )
        Scaffold(
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = index == selectedItem ,
                            label = { Text(item.label) },
                            icon = {
                                Icon(imageVector = item.icon, contentDescription = item.label)
                            },
                            onClick = {
                                selectedItem = index
                                navController.navigate(item.route.route){
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            if (mainViewModel.state)
            {
                color = Color.Black
            }
            else
            {
                color = Color.White
            }

            Box(modifier = Modifier.background(color)) {
                NavigationWrapper(navController,mainViewModel,bdViewModel)
            }
        }
    }
