package com.rar.devs.weatherapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.rar.devs.weatherapp.data.mappers.toWeatherInfo
import com.rar.devs.weatherapp.data.remote.WeatherApi
import com.rar.devs.weatherapp.domain.repository.WeatherRepository
import com.rar.devs.weatherapp.domain.util.Resource
import com.rar.devs.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(data = api.getWeatherData(lat = lat, long = long).toWeatherInfo())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown Error occurred")
        }
    }
}