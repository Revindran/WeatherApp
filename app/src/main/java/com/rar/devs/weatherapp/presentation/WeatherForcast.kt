package com.rar.devs.weatherapp.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rar.devs.weatherapp.presentation.ui.theme.DarkBlue

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForCast(
    viewModel: WeatherViewModel,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(bottom = 32.dp)) {
        LazyColumn(content = {
            items(7) { i ->
                viewModel.state.weatherInfo?.weatherDataPerDay?.get(i)?.let { data ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = viewModel.titleText(i), fontSize = 20.sp, color = Color.White)
                        Spacer(modifier = modifier.height(8.dp))
                        Card(
                            backgroundColor = DarkBlue,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            LazyRow(content = {
                                items(data) { weatherData ->
                                    HourlyWeatherDisplay(
                                        weatherData = weatherData,
                                        modifier = Modifier
                                            .height(100.dp)
                                            .padding(16.dp)
                                    )
                                }
                            })
                        }
                    }
                }
            }
        })
    }
}