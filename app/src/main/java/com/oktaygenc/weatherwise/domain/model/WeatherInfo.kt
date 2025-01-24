package com.oktaygenc.weatherwise.domain.model

data class WeatherInfo(
    val location: Location,
    val weather: Weather,
    val forecasts: List<ForecastInfo>,
)

data class Location(
    val cityName: String,
    val country: String,
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

data class ForecastInfo(
    val timestamp: Long,
    val temperature: Double,
    val description: String,
    val iconUrl: String,
    val date: String,
    val humidity: Int,
    val feelsLike: Double,
    )

data class WeatherCondition(
    val timestamp: Long,
    val dateText: String,
    val description: String,
    val iconUrl: String,
)

