package com.rar.devs.weatherapp.domain.weather

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val weatherType: WeatherType,
    val humidity: Double,
    val pressure: Double,
    val windSpeed: Double
)
