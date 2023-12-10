package com.example.page1

import com.google.gson.annotations.SerializedName

data class CityDetails(
    @SerializedName("_id") val id: String,
    @SerializedName("event_title") val eventTitle: String,
    val location: String,
    val price: Int,
    val venue: String,
    @SerializedName("event_type") val eventType: String,
    val description: String,
    @SerializedName("remaining_tickets") val remainingTickets: Int,
    @SerializedName("event_date") val eventDate: String,
    val image1: String,
    val image2: String,
    val image3: String,
    val image4: String
)