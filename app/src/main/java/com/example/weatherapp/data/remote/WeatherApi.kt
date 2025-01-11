package com.example.weatherapp.data.remote

import com.example.weatherapp.data.moodel.ApiWeather
import com.example.weatherapp.utils.ApiParameter
import com.example.weatherapp.utils.K
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(K.END_POINT)
    suspend fun getWeatherData(
        @Query(ApiParameter.LATITUDE) latitude : Float = -6.76f,
        @Query(ApiParameter.LONGITUDE) longitude : Float = 13.41f,
        @Query(ApiParameter.CURRENT_WEATHER) currentWeather : Array<String> = arrayOf(
            "temperature_2m",
            "relative_humidity_2m",
            "is_day",
            "rain",
            "snowfall",
            "weather_code",
            "wind_speed_10m",
            "wind_direction_10m"
        ),
        @Query(ApiParameter.HOURLY) hourly : Array<String> = arrayOf(
            "weather_code",
            "temperature_2m",
        ),
        @Query(ApiParameter.DAILY) daily : Array<String> = arrayOf(
            "weather_code",
            "temperature_2m_max",
            "temperature_2m_min",
            "sunrise",
            "sunset",
            "uv_index_max",
            "wind_speed_10m_max",
            "wind_direction_10m_dominant"
        ),
        @Query(ApiParameter.TIME_FORMAT) timeformat : String = "unixtime",
        @Query(ApiParameter.TIME_ZONE) timezone : String = "Africa/Dar_es_Salaam"
    ): ApiWeather

}