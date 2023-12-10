package com.example.page1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendServiceFactory {

    private const val BASE_URL = "https://event-snap.cyclic.app/"

    fun create(): BackendService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(BackendService::class.java)
    }


}