package com.example.weatherapp.presentation.home

import com.example.weatherapp.domain.model.Daily
import com.example.weatherapp.domain.model.Weather

data class HomeState(
    val data : Weather?=null,
    val error : String? = null,
    val isLoading : Boolean?  = false,
    val dailyWeatherInfo : Daily.WeatherInfo? = null
)