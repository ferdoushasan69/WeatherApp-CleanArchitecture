package com.example.weatherapp.data.mapperimpl

import com.example.weatherapp.data.mapper.ApiMapper
import com.example.weatherapp.data.moodel.ApiDaily
import com.example.weatherapp.domain.model.Daily
import com.example.weatherapp.utils.Util
import com.example.weatherapp.utils.Util.WeatherInfoItem

class ApiDailyMapperImpl : ApiMapper<Daily, ApiDaily> {
    override fun mapToDomain(apiEntity: ApiDaily): Daily {
        return Daily(
            time = parseTime(apiEntity.time),
            weather_code = parseWeatherCode(apiEntity.weatherCode),
            temperature_2m_max =  apiEntity.temperature2mMax,
            temperature_2m_min = apiEntity.temperature2mMin,
            sunrise = apiEntity.sunrise.map { Util.formatUnixDate("HH:mm",it) },
            sunset = apiEntity.sunset.map { Util.formatUnixDate("HH:mm",it) },
            uv_index_max = apiEntity.uvIndexMax,
            wind_speed_10m_max = apiEntity.windSpeed10mMax,
            wind_direction_10m_dominant = parseDirection(apiEntity.windDirection10mDominant)
        )
    }

    private fun parseDirection(direction : List<Double>) : List<String>{
        return direction.map { Util.getWindDirection(it) }
    }
    private fun  parseWeatherCode(code : List<Int>) : List<WeatherInfoItem>{
        return code.map { Util.getWeatherInfo(it) }
    }
    private fun parseTime(time : List<Long>) : List<String>{
        return time.map { Util.formatUnixDate("E",it) }
    }
}