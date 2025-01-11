package com.example.weatherapp.di

import com.example.weatherapp.data.mapper.ApiMapper
import com.example.weatherapp.data.mapperimpl.ApiCurrentMapperImpl
import com.example.weatherapp.data.mapperimpl.ApiDailyMapperImpl
import com.example.weatherapp.data.mapperimpl.ApiHourlyMapperImpl
import com.example.weatherapp.data.mapperimpl.ApiWeatherImpl
import com.example.weatherapp.data.moodel.ApiCurrent
import com.example.weatherapp.data.moodel.ApiDaily
import com.example.weatherapp.data.moodel.ApiHourly
import com.example.weatherapp.data.moodel.ApiWeather
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.model.Daily
import com.example.weatherapp.domain.model.Hourly
import com.example.weatherapp.domain.model.Weather
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier


@InstallIn(SingletonComponent::class)
@Module
object MapperModule {
    @ApiDailyMapperAnnotation
    @Provides
    fun provideApiDailyMapper(): ApiMapper<Daily, ApiDaily> {
        return ApiDailyMapperImpl()
    }
    @ApiHourlyMapperAnnotation
    @Provides
    fun provideApiHourlyMapper(): ApiMapper<Hourly, ApiHourly> {
        return ApiHourlyMapperImpl()
    }
    @ApiCurrentMapperAnnotation
    @Provides
    fun provideCurrentMapper(): ApiMapper<CurrentWeather, ApiCurrent> {
        return ApiCurrentMapperImpl()
    }
    @ApiWeatherMapperAnnotation
    @Provides
    fun provideApiWeatherMapper(
        currentWeatherMapper: ApiMapper<CurrentWeather, ApiCurrent>,
        dailyWeatherMapper: ApiMapper<Daily, ApiDaily>,
        hourlyMapper: ApiMapper<Hourly, ApiHourly>,
    ): ApiMapper<Weather, ApiWeather> {
        return ApiWeatherImpl(currentWeatherMapper, dailyWeatherMapper, hourlyMapper)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiDailyMapperAnnotation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiHourlyMapperAnnotation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiCurrentMapperAnnotation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiWeatherMapperAnnotation

