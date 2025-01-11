package com.example.weatherapp.di

import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.utils.K
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Singleton
    @Provides
    fun provideApiService(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}