package com.example.displayweatherapp.api


import com.example.displayweatherapp.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("v1/current.json")
    suspend fun getweather(
        @Query("Key")
        apiKey: String,
        @Query("q")
        citycode: String,
        @Query("aqi")
        airquality: String ="no"
    ): Response<WeatherResponse>
}