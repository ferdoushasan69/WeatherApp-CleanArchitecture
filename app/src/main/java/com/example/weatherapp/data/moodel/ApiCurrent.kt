package com.example.weatherapp.data.moodel


import com.google.gson.annotations.SerializedName

data class ApiCurrent(
    @SerializedName("interval")
    val interval: Int,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("rain")
    val rain: Int,
    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: Int,
    @SerializedName("snowfall")
    val snowfall: Int,
    @SerializedName("temperature_2m")
    val temperature2m: Double,
    @SerializedName("time")
    val time: Long,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("wind_direction_10m")
    val windDirection10m: Double,
    @SerializedName("wind_speed_10m")
    val windSpeed10m: Double
)