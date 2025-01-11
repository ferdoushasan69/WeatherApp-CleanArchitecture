package com.example.weatherapp.domain.model

import com.example.weatherapp.utils.Util.WeatherInfoItem

data class Daily(
    private val time: List<String>,
    private val weather_code: List<WeatherInfoItem>,
    private val temperature_2m_max: List<Double>,
    private val temperature_2m_min: List<Double>,
    private val sunrise: List<String>,
    private val sunset: List<String>,
    private val uv_index_max: List<Double>,
    private val wind_speed_10m_max: List<Double>,
    private val wind_direction_10m_dominant : List<String>
) {
    val weatherInfo: List<WeatherInfo>
        get() {
            val dailyWeatherInfo = mutableListOf<WeatherInfo>()
            for (i in temperature_2m_min.indices) {
                dailyWeatherInfo.add(

                WeatherInfo(
                    time = time[i],
                    weather_code = weather_code[i],
                    temperature_2m_max = temperature_2m_max[i],
                    temperature_2m_min = temperature_2m_min[i],
                    sunrise = sunrise[i],
                    sunset = sunset[i],
                    uv_index_max = uv_index_max[i],
                    wind_speed_10m_max = wind_speed_10m_max[i],
                    wind_direction_10m_dominant = wind_direction_10m_dominant[i]
                )
                )

            }
            return dailyWeatherInfo
        }

    data class WeatherInfo(
        val time: String,
        val weather_code: WeatherInfoItem,
        val temperature_2m_max: Double,
        val temperature_2m_min: Double,
        val sunrise: String,
        val sunset: String,
        val uv_index_max: Double,
        val wind_speed_10m_max: Double,
        val wind_direction_10m_dominant : String

        )
}
