package com.oktaygenc.weatherwise.domain.model

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
    val condition: WeatherCondition
)

data class WeatherCondition(
    val description: String,
    val iconUrl: String
)