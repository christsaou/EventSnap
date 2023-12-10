package com.example.page1


import com.google.gson.annotations.SerializedName

data class EventsDataResponse(
    @SerializedName("description")
    var description: String?,
    @SerializedName("event_date")
    var eventDate: String?,
    @SerializedName("event_title")
    var eventTitle: String?,
    @SerializedName("event_type")
    var eventType: String?,
    @SerializedName("_id")
    var id: String?,
    @SerializedName("image1")
    var image1: String?,
    @SerializedName("image2")
    var image2: String?,
    @SerializedName("image3")
    var image3: String?,
    @SerializedName("image4")
    var image4: String?,
    @SerializedName("location")
    var location: String?,
    @SerializedName("price")
    var price: Int?,
    @SerializedName("remaining_tickets")
    var remainingTickets: Int?,
    @SerializedName("venue")
    var venue: String?
)