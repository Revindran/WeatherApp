package com.rar.devs.weatherapp.presentation

import com.rar.devs.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)
