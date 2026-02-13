package com.example.apiappactividad.data
import com.example.apiappactividad.data.mimgodel.Result
import kotlin.collections.emptyList
import com.example.apiappactividad.data.network.RetrofitClient


class Repository {
    // La funció ara retorna una llista de 'Result'
    suspend fun getCharactersFromApi(): List<Result> {
        return try {
            val response = RetrofitClient.instance.getCharacters()

            if (response.isSuccessful) {
                // Si va bé, retornem la llista que hi ha dins de .results
                // Si és null, retornem llista buida
                response.body()?.results ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}