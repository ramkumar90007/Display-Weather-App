package com.example.displayweatherapp.models



import java.io.Serializable


data class Weather(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val name: String,
    val region: String,
    val country: String,
    val lat: String,
    val long: String,
    val tz_id: String,
    val localtime_epoch : String,
    val localtime : String
) : Serializable

annotation class PrimaryKey(val autoGenerate: Boolean)
