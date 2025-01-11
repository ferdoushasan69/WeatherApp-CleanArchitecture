package com.example.weatherapp.data.mapperimpl

import com.example.weatherapp.data.mapper.ApiMapper
import com.example.weatherapp.data.moodel.ApiHourly
import com.example.weatherapp.domain.model.Hourly
import com.example.weatherapp.utils.Util

class ApiHourlyMapperImpl : ApiMapper<Hourly, ApiHourly> {
    override fun mapToDomain(apiEntity: ApiHourly): Hourly {
        return Hourly(
            time = apiEntity.time.map { Util.formatUnixDate("HH:mm",it) },
            temperature_2m = apiEntity.temperature2m,
            weatherCode = apiEntity.weatherCode.map { Util.getWeatherInfo(it) }
        )
    }
}