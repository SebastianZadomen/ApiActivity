package com.example.apiappactividad.data.network

import com.example.apiappactividad.data.model.DadesAPI
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("characters")
    suspend fun getCharacters(): Response<DadesAPI>
}