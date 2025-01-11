package com.example.weatherapp.data.repositoryimpl

import android.content.ContentValues.TAG
import android.util.Log
import com.example.weatherapp.data.mapperimpl.ApiWeatherImpl
import com.example.weatherapp.data.moodel.ApiWeather
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi : WeatherApi,
    private val apiWeatherImpl: ApiWeatherImpl
) : WeatherRepository {
    override fun getWeatherData(): Flow<Response<Weather>> = flow {
        emit(Response.Loading())

        val apiData = weatherApi.getWeatherData()
        Log.d(TAG, "getWeatherData: $apiData")
        val weather = apiWeatherImpl.mapToDomain(apiData)
        emit(Response.Success(weather))
    }.catch {
        emit(Response.Error(it.message.toString()))
        Log.d(TAG, "getWeatherData: ${it.message}")
    }
}