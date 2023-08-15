package com.example.displayweatherapp.models




data class WeatherResponse(
    val weather: List<Weather>,
    val status: String,

)