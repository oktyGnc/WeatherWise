package com.oktaygenc.weatherwise.dto

import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(
    @SerializedName("list") val forecasts: List<ForecastDto>,
    @SerializedName("city") val city: CityDto
)

data class ForecastDto(
    @SerializedName("dt") val timestamp: Long,
    @SerializedName("main") val main: MainWeatherDto,
    @SerializedName("weather") val weather: List<WeatherDescriptionDto>,
    @SerializedName("dt_txt") val dateText: String
)

data class MainWeatherDto(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int
)

data class WeatherDescriptionDto(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class CityDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("timezone") val timezone: Int
)