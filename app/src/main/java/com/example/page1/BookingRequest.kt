package com.example.page1

import com.google.gson.annotations.SerializedName

data class BookingRequest(
    @SerializedName("eventId")
    val eventId: String,
    @SerializedName("numTickets")
    val numTickets: Int,
    @SerializedName("totalPrice")
    val totalPrice: Double
)