package com.rar.devs.weatherapp.domain.repository

import com.rar.devs.weatherapp.domain.util.Resource
import com.rar.devs.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}