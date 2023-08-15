package com.example.displayweatherapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.example.displayweatherapp.models.WeatherResponse
import com.example.displayweatherapp.repository.WeatherRepository
import com.example.displayweatherapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel(val weatherRepository: WeatherRepository) : ViewModel() {

    val weatherLocation: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()

    fun getWeatherLocation(cityCode: String) = viewModelScope.launch {
        weatherLocation.postValue(Resource.Loading())
        val response = weatherRepository.getweather(cityCode)
        Log.d("Weather Response", response.body().toString())
        weatherLocation.postValue(handleWeatherLocationResponse(response))
    }


    private fun handleWeatherLocationResponse(response: Response<WeatherResponse>): Resource<WeatherResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}












