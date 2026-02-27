    package com.example.apiappactividad

    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.BoxWithConstraints
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Favorite
    import androidx.compose.material.icons.filled.Home
    import androidx.compose.material.icons.filled.Settings
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Icon
    import androidx.compose.material3.NavigationBar
    import androidx.compose.material3.NavigationBarItem
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
    import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableIntStateOf
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.platform.LocalContext
    import androidx.lifecycle.viewmodel.compose.viewModel
    import androidx.navigation.compose.rememberNavController
    import com.example.apiappactividad.data.BD.AppDatabase
    import com.example.apiappactividad.navigation.Destinations
    import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
    import androidx.compose.ui.unit.dp
    import com.example.apiappactividad.ui.theme.ApiAppActividadTheme
    import com.example.apiappactividad.ui.viewmodel.MainViewModel
    import com.example.apiappactividad.navigation.NavigationItem
    import com.example.apiappactividad.navigation.NavigationWrapper
    import com.example.apiappactividad.ui.viewmodel.CharacterViewModel
    import com.example.apiappactividad.ui.viewmodel.CharacterViewModelFactory
    import com.example.apiappactividad.ui.viewmodel.ViewDesign


    class MainActivity : ComponentActivity() {

        @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                ApiAppActividadTheme {
                    val windowSize = calculateWindowSizeClass(this)
                    MyApp(windowSize.widthSizeClass)


                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyApp(widthSizeClass: WindowWidthSizeClass) {
        var selectedItem by remember { mutableIntStateOf(0) }
        var color by remember { mutableStateOf(Color.White)  }

        val navController = rememberNavController()
        val mainViewModel: MainViewModel = viewModel()
        val viewDesignResponsi : ViewDesign = viewModel()
        val context = LocalContext.current
        val database = AppDatabase.getDatabase(context)
        val dao = database.characterDao()

        val factory = CharacterViewModelFactory(dao)

        val bdViewModel: CharacterViewModel = viewModel(factory = factory)
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            if (this.maxWidth < 412.dp) {
                viewDesignResponsi.gridCells = 2
                viewDesignResponsi.imageSizeScreen1 = 50.dp
                viewDesignResponsi.paddingStart = 10.dp
                viewDesignResponsi.paddingEnd = 10.dp
                viewDesignResponsi.fontSizeName = 20
                viewDesignResponsi.fontSizeDetail = 15
                viewDesignResponsi.imageSizeDetailScreen = 250.dp


            } else {
                viewDesignResponsi.gridCells = 4
                viewDesignResponsi.imageSizeScreen1 = 60.dp
                viewDesignResponsi.paddingStart = 100.dp
                viewDesignResponsi.paddingEnd = 60.dp
                viewDesignResponsi.fontSizeName = 40
                viewDesignResponsi.fontSizeDetail = 25
                viewDesignResponsi.imageSizeDetailScreen = 450.dp

            }
        }


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
                NavigationWrapper(navController,mainViewModel,bdViewModel,viewDesignResponsi)
            }
        }
    }
