package com.example.apiappactividad.data.mimgodel


data class Result (
    val name: String,
    val img: String,
    val gender: String,
    val age: String,
    val roles : List<String>,
    val status : String,
    val alias : List<String>,
    val birthplace : String,
   // val relatives: List<String>
)
