package com.rar.devs.weatherapp.domain.weather

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentDayWeatherData: WeatherData?
)
