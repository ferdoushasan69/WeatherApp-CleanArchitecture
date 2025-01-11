package com.example.weatherapp.presentation.daily

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.utils.Response
import kotlinx.coroutines.launch

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    var dailyState by mutableStateOf(DailyState())
        private set

    init {
        viewModelScope.launch {
            weatherRepository.getWeatherData().collect { response ->
                when (response) {
                    is Response.Loading -> {
                        dailyState = dailyState.copy(isLoading = true)
                    }

                    is Response.Success -> {
                        dailyState = dailyState.copy(
                            daily = response.data?.daily,
                            isLoading = false,
                            error = null
                        )
                    }

                    is Response.Error -> {
                        dailyState = dailyState.copy(isLoading = false, error = response.message)
                    }
                }
            }
        }
    }

}
