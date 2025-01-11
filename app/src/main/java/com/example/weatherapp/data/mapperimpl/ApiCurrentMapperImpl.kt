package com.example.weatherapp.data.mapperimpl

import com.example.weatherapp.data.mapper.ApiMapper
import com.example.weatherapp.data.moodel.ApiCurrent
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.utils.Util
import com.example.weatherapp.utils.Util.WeatherInfoItem

class ApiCurrentMapperImpl : ApiMapper<CurrentWeather, ApiCurrent> {
    override fun mapToDomain(apiEntity: ApiCurrent): CurrentWeather {
        return CurrentWeather(
            temperature2m = apiEntity.temperature2m,
            time = parseTime(apiEntity.time),
            weatherCode =parseWeatherCode(apiEntity.weatherCode),
            windDirection10m = parseWindDirection(apiEntity.windDirection10m),
            windSpeed10m = apiEntity.windSpeed10m,
            is_day = apiEntity.isDay == 1
        )
    }

    private fun parseWindDirection(windDirection : Double): String{
        return Util.getWindDirection(windDirection)
    }

    private fun parseWeatherCode(weatherCode : Int): WeatherInfoItem{
        return Util.getWeatherInfo(weatherCode)
    }
    private fun parseTime(time : Long) : String{
        return Util.formatUnixDate("HH:mm",time)
    }
}