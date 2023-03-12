package com.rar.devs.weatherapp.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rar.devs.weatherapp.domain.location.LocationTracker
import com.rar.devs.weatherapp.domain.repository.WeatherRepository
import com.rar.devs.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set


    private fun getDates(dateToAdd: Long): String =
        LocalDateTime.now().plusDays(dateToAdd).format(DateTimeFormatter.ofPattern("EEE, MMMM dd"))

    fun titleText(dayOfWeek: Int): String = when (dayOfWeek) {
        0 -> "Today (${getDates(0)})"
        1 -> "Tomorrow (${getDates(1)})"
        else -> getDates(dayOfWeek.toLong())
    }

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when (val result =
                    repository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            isLoading = false,
                            error = null,
                            weatherInfo = result.data
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message,
                            weatherInfo = null
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location, make sure to grant permission and enable GPS"
                )
            }
        }
    }
}