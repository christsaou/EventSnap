package com.example.page1

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface BackendService {
    @GET("api/cities/getallcities")
    fun getCities(): Call<List<City>>

    @GET("api/events/getallevents")
    fun getEvents(
        @Query("location") location: String?,
        @Query("event_type") eventType: String?
    ): Call<List<EventsDataResponse>>

    @PUT("api/events/bookevent/{eventId}")
    fun bookEvent(
        @Path("eventId") eventId: String,
        @Body bookingRequest: BookingRequest
    ): Call<Void>
}
