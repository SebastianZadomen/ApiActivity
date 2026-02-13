package com.example.apiappactividad.data.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // 1. La URL Base on farem totes les peticions
    // IMPORTANT: Ha d'acabar amb una barra '/' final
    private const val BASE_URL = "https://api.attackontitanapi.com/"

    // 2. La instància de Retrofit (La màquina configurada)
    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Això tradueix el JSON automàticament
            .build()
            .create(ApiService::class.java) // Creem la implementació de la interfície
    }
}