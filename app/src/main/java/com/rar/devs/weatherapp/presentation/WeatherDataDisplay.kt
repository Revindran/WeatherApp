package com.rar.devs.weatherapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.rar.devs.weatherapp.presentation.ui.theme.DarkBlue

@Composable
fun WeatherDataDisplay(
    value: String,
    unit: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    iconTint: Color = Color.White
) {
    Card(
        backgroundColor = DarkBlue,
        shape = RoundedCornerShape(50.dp)
    ) {
        Row(modifier = modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "$value $unit", style = textStyle)
        }
    }
}