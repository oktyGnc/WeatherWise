package com.oktaygenc.weatherwise.ui.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.oktaygenc.weatherwise.R
import com.oktaygenc.weatherwise.domain.model.WeatherInfo

@Composable
fun CurrentWeatherSection(weatherInfo: WeatherInfo) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        Row {
            Icon(
                painter = painterResource(R.drawable.ic_marker),
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = "${weatherInfo.location.cityName}, ${weatherInfo.location.country}",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium
            )
        }


        AsyncImage(
            model = weatherInfo.weather.condition.iconUrl,
            contentDescription = "Weather Icon",
            modifier = Modifier.size(150.dp)
        )

        Text(
            text = "${weatherInfo.weather.temperature.toInt()}°C",
            color = Color.White,
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = weatherInfo.weather.condition.description.replaceFirstChar { it.uppercase() },
            color = Color.White,
            fontSize = 20.sp
        )
    }
}