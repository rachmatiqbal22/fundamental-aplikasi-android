package com.example.test.data.response

import com.google.gson.annotations.SerializedName

data class EventResponse(

	@field:SerializedName("listEvents")
	val listEvents: List<ListEventsItem>?,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class ListEventsItem(

	@field:SerializedName("summary")
	val summary: String,

	@field:SerializedName("mediaCover")
	val mediaCover: String,

	@field:SerializedName("registrants")
	val registrants: Int,

	@field:SerializedName("imageLogo")
	val imageLogo: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("ownerName")
	val ownerName: String? = null,

	@field:SerializedName("cityName")
	val cityName: String? = null,

	@field:SerializedName("quota")
	val quota: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("beginTime")
	val beginTime: String? = null,

	@field:SerializedName("endTime")
	val endTime: String? = null,

	@field:SerializedName("category")
	val category: String? = null
)