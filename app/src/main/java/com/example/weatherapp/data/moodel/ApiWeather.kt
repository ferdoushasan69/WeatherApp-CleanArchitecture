package com.example.weatherapp.data.moodel


import com.google.gson.annotations.SerializedName

data class ApiWeather(
    @SerializedName("current")
    val current: ApiCurrent,
    @SerializedName("current_units")
    val currentUnits: CurrentUnits,
    @SerializedName("daily")
    val daily: ApiDaily,
    @SerializedName("daily_units")
    val dailyUnits: DailyUnits,
    @SerializedName("elevation")
    val elevation: Int,
    @SerializedName("generationtime_ms")
    val generationtimeMs: Double,
    @SerializedName("hourly")
    val hourly: ApiHourly,
    @SerializedName("hourly_units")
    val hourlyUnits: HourlyUnits,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)