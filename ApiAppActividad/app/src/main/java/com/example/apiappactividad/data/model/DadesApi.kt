package com.example.apiappactividad.data.model

import com.example.apiappactividad.data.mimgodel.Result


data class DadesAPI(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)