package com.example.weatherapp.data.moodel


import com.google.gson.annotations.SerializedName

data class ApiDaily(
    @SerializedName("sunrise")
    val sunrise: List<Long>,
    @SerializedName("sunset")
    val sunset: List<Long>,
    @SerializedName("temperature_2m_max")
    val temperature2mMax: List<Double>,
    @SerializedName("temperature_2m_min")
    val temperature2mMin: List<Double>,
    @SerializedName("time")
    val time: List<Long>,
    @SerializedName("uv_index_max")
    val uvIndexMax: List<Double>,
    @SerializedName("weather_code")
    val weatherCode: List<Int>,
    @SerializedName("wind_direction_10m_dominant")
    val windDirection10mDominant: List<Double>,
    @SerializedName("wind_speed_10m_max")
    val windSpeed10mMax: List<Double>
)