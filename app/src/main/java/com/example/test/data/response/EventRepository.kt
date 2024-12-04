package com.example.test.data.response

import com.example.test.data.retrofit.ApiService
import retrofit2.Response

class EventRepository {
    private val apiService = ApiService.create()

    suspend fun getEvents(active: Int): Response<EventResponse> {
        return apiService.getEvents(active)
    }
}
