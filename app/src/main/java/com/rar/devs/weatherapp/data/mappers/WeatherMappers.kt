package com.rar.devs.weatherapp.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.rar.devs.weatherapp.data.remote.WeatherDataDto
import com.rar.devs.weatherapp.data.remote.WeatherDto
import com.rar.devs.weatherapp.domain.weather.WeatherData
import com.rar.devs.weatherapp.domain.weather.WeatherInfo
import com.rar.devs.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int, val data: WeatherData
)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val humidity = relativeHumidity[index]
        val pressure = pressures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        IndexedWeatherData(
            index = index, data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                weatherType = WeatherType.fromWMO(weatherCode),
                humidity = humidity,
                pressure = pressure,
                windSpeed = windSpeed
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentDayWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 59) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap, currentDayWeatherData = currentDayWeatherData
    )
}