package com.example.apiappactividad.navigation

import kotlinx.serialization.Serializable
/*
sealed class Routes(val route: String) {
    object Pantalla1:Routes("pantalla1")
    object Pantalla2:Routes("pantalla2")
    object Pantalla3:Routes("pantalla3")

    object DetailScreen:Routes("detailScreen")
}*/

sealed class Destinations (val route: String){
    object Screen1 : Destinations("screen1")

    object Screen2 : Destinations("screen2")

    object Screen3 : Destinations("screen3")

    // NOVA PANTALLA (No surt al menú, però existeix)
   object DetailScreen : Destinations("detailScreen")
}