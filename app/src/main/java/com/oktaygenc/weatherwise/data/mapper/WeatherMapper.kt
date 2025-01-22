package com.oktaygenc.weatherwise.data.mapper

import com.oktaygenc.weatherwise.data.local.WeatherEntity
import com.oktaygenc.weatherwise.domain.model.Location
import com.oktaygenc.weatherwise.domain.model.Weather
import com.oktaygenc.weatherwise.domain.model.WeatherCondition
import com.oktaygenc.weatherwise.domain.model.WeatherInfo
import com.oktaygenc.weatherwise.dto.WeatherResponseDto
import com.oktaygenc.weatherwise.utils.Constants

object WeatherMapper {

    // DTO to Entity
    fun WeatherResponseDto.toEntity(): WeatherEntity {
        val forecast = this.forecasts.firstOrNull() // First forecast for simplicity
        val weatherDescription = forecast?.weather?.firstOrNull()
        return WeatherEntity(
            cityName = this.city.name,
            country = this.city.country,
            temperature = forecast?.main?.temperature,
            weatherDescription = weatherDescription?.description,
            weatherIcon = weatherDescription?.icon
        )
    }

    // Entity to Domain Model
    fun WeatherEntity.toDomainModel(): WeatherInfo {
        return WeatherInfo(
            location = Location(
                cityName = this.cityName ?: "Unknown",
                country = this.country ?: "Unknown"
            ),
            weather = Weather(
                temperature = this.temperature ?: 0.0,
                condition = WeatherCondition(
                    description = this.weatherDescription ?: "Unknown",
                    iconUrl = Constants.getIconUrl(this.weatherIcon)
                )
            )
        )
    }
}