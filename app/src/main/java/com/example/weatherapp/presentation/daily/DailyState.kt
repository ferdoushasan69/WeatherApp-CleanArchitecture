package com.example.weatherapp.presentation.daily

import com.example.weatherapp.domain.model.Daily

data class DailyState(
    val daily : Daily?=null,
    val isLoading : Boolean?=null,
    val error : String?=null
)
