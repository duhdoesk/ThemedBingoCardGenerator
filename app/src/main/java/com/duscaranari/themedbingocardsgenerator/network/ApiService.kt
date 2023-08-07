package com.duscaranari.themedbingocardsgenerator.network

import com.duscaranari.themedbingocardsgenerator.model.AppData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("app_data.json")
    suspend fun getAppData() : AppData
}

object RetrofitInstance {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://duhdoesk.github.io/ThemedBingoCardGenerator/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}