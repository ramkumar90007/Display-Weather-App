package com.example.displayweatherapp.repository


import com.example.displayweatherapp.api.RetrofitInstance
import com.example.displayweatherapp.util.Constants.Companion.API_KEY

class WeatherRepository {
    suspend fun getweather(city: String) =
        RetrofitInstance.api.getweather(API_KEY,city)



}