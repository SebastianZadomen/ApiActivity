package com.example.apiappactividad.data.model

import android.R

data class Result (
    val id : Int,
    val name: String,
    val img: String,
    val gender: String,
    val age: String,
    val roles : List<String>,
    val status : String,
    val alias : List<String>,
    val birthplace : String,
    val favorites : Boolean = false
   // val relatives: List<String>
)
