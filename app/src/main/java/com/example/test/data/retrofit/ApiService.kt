package com.example.test.data.retrofit

import com.example.test.data.response.DetailEventResponse
import com.example.test.data.response.EventResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("events")
    suspend fun getEvents(@Query("active") active: Int): Response<EventResponse>

    @GET("events/{id}")
    suspend fun getDetailEvent(@Path("id") id: String): Response<DetailEventResponse>

    companion object {
        private const val BASE_URL = "https://event-api.dicoding.dev/"

        fun create(): ApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
