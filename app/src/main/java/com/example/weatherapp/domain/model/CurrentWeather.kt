package com.example.weatherapp.domain.model

import com.example.weatherapp.utils.Util.WeatherInfoItem

data class CurrentWeather(
    val temperature2m: Double,
    val time: String,
    val weatherCode: WeatherInfoItem,
    val windDirection10m: String,
    val windSpeed10m: Double,
    val is_day : Boolean
)
