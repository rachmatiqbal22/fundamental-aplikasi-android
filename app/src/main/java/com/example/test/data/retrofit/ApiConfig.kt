package com.example.test.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private const val BASE_URL = "https://event-api.dicoding.dev/"

    fun getApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}
