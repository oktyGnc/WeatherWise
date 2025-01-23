package com.oktaygenc.weatherwise.domain.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class WeatherInfo(
    val location: Location,
    val weather: Weather
)

data class Location(
    val cityName: String,
    val country: String
)

data class Weather(
    val temperature: Double,
    val condition: WeatherCondition,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int,
)

data class WeatherCondition(
    val timestamp: Long,
    val description: String,
    val iconUrl: String
) {
    val formattedDate: String
        get() {
            val date = Date(timestamp)
            val formatter = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.getDefault())
            return formatter.format(date)
        }
}