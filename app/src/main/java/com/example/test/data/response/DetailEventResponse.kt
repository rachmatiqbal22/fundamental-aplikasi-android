package com.example.test.data.response

data class DetailEventResponse(
    val id: Int,
    val name: String,
    val summary: String,
    val description: String,
    val imageLogo: String,
    val mediaCover: String,
    val category: String,
    val ownerName: String,
    val cityName: String,
    val beginTime: String,
    val endTime: String,
    val link: String,
    val quota: Int,
    val registrants: Int
)

