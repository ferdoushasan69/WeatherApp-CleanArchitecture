package com.example.weatherapp.data.moodel


import com.google.gson.annotations.SerializedName

data class ApiHourly(
    @SerializedName("rain")
    val rain: List<Double>,
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,
    @SerializedName("time")
    val time: List<Long>,
    @SerializedName("weather_code")
    val weatherCode: List<Int>,
    @SerializedName("wind_speed_10m")
    val windSpeed10m: List<Double>,
    @SerializedName("wind_speed_80m")
    val windSpeed80m: List<Double>
)