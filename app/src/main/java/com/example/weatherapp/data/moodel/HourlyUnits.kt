package com.example.weatherapp.data.moodel


import com.google.gson.annotations.SerializedName

data class HourlyUnits(
    @SerializedName("rain")
    val rain: String,
    @SerializedName("temperature_2m")
    val temperature2m: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("weather_code")
    val weatherCode: String,
    @SerializedName("wind_speed_10m")
    val windSpeed10m: String,
    @SerializedName("wind_speed_80m")
    val windSpeed80m: String
)