package com.oktaygenc.weatherwise.ui.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oktaygenc.weatherwise.R
import com.oktaygenc.weatherwise.domain.model.WeatherDetail
import com.oktaygenc.weatherwise.domain.model.WeatherInfo



@Composable
fun WeatherDetailsSection(weatherInfo: WeatherInfo) {
    val details = remember(weatherInfo) {
        listOf(
            WeatherDetail(
                iconRes = R.drawable.ic_temperature,
                title = "Feels Like",
                value = "${weatherInfo.weather.feelsLike.toInt()}°C"
            ),
            WeatherDetail(
                iconRes = R.drawable.ic_pressure,
                title = "Pressure",
                value = "${weatherInfo.weather.pressure} hPa"
            ),
            WeatherDetail(
                iconRes = R.drawable.ic_humidity,
                title = "Humidity",
                value = "${weatherInfo.weather.humidity}%"
            )
        )
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        items(
            items = details,
            key = { it.title }
        ) { detail ->
            WeatherDetailCard(detail)
        }
    }
}