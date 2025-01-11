package com.example.weatherapp.data.mapperimpl

import com.example.weatherapp.data.mapper.ApiMapper
import com.example.weatherapp.data.moodel.ApiCurrent
import com.example.weatherapp.data.moodel.ApiDaily
import com.example.weatherapp.data.moodel.ApiHourly
import com.example.weatherapp.data.moodel.ApiWeather
import com.example.weatherapp.di.ApiCurrentMapperAnnotation
import com.example.weatherapp.di.ApiDailyMapperAnnotation
import com.example.weatherapp.di.ApiHourlyMapperAnnotation
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.model.Daily
import com.example.weatherapp.domain.model.Hourly
import com.example.weatherapp.domain.model.Weather
import javax.inject.Inject

class ApiWeatherImpl @Inject constructor(
    @ApiCurrentMapperAnnotation private val currentWeatherMapper: ApiMapper<CurrentWeather, ApiCurrent>,
    @ApiDailyMapperAnnotation private val dailyMapper: ApiMapper<Daily, ApiDaily>,
    @ApiHourlyMapperAnnotation private val hourlyMapper: ApiMapper<Hourly, ApiHourly>,
) : ApiMapper<Weather, ApiWeather> {
    override fun mapToDomain(apiEntity: ApiWeather): Weather {
        return Weather(
            currentWeather = currentWeatherMapper.mapToDomain(apiEntity.current),
            daily = dailyMapper.mapToDomain(apiEntity.daily),
            hourly = hourlyMapper.mapToDomain(apiEntity.hourly)
        )
    }
}