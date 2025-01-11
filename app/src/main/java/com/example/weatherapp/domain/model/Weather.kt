package com.example.weatherapp.domain.model

import com.example.weatherapp.data.moodel.ApiHourly


data class Weather(
    val currentWeather: CurrentWeather,
    val daily: Daily,
    val hourly: Hourly
)
