package com.example.weatherapp.presentation.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.utils.Response
import com.example.weatherapp.utils.Util
import kotlinx.coroutines.launch

@HiltViewModel
class HomeVIewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {
     var homeState by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            weatherRepository.getWeatherData().collect { response ->
                when (response) {
                    is Response.Loading -> {
                       homeState=  homeState.copy(isLoading = true)
                    }

                    is Response.Success -> {
                       homeState = homeState.copy(data = response.data, isLoading = false, error = null)
                        val todayDailyWeatherInfo = response.data?.daily?.weatherInfo?.find {
                            Util.isTodayDate(it.time)
                        }
                        homeState = homeState.copy(dailyWeatherInfo = todayDailyWeatherInfo)

                    }
                    is Response.Error ->{
                       homeState = homeState.copy(error = response.message, isLoading = false)

                    }
                    else -> {
                        Log.d(TAG, "data is not here ")
                    }
                }
            }
        }
    }
}