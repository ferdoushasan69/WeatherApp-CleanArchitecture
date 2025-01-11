package com.example.weatherapp.domain.model

import com.example.weatherapp.utils.Util.WeatherInfoItem

data class Hourly(
    private val time: List<String>,
    private val temperature_2m: List<Double>,
    private val weatherCode: List<WeatherInfoItem>,
) {
    val weatherInfo: List<HourlyInfoItem>
        get() {
            return time.mapIndexed { index, item ->
                HourlyInfoItem(
                    time = time[index],
                    temperature_2m = temperature_2m[index],
                    weatherCode = weatherCode[index]
                )
            }
        }

    data class HourlyInfoItem(
        val time: String,
        val temperature_2m: Double,
        val weatherCode: WeatherInfoItem,
    )
}
