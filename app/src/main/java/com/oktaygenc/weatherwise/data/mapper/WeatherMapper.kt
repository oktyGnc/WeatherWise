package com.oktaygenc.weatherwise.data.mapper

import com.oktaygenc.weatherwise.data.local.entity.WeatherEntity
import com.oktaygenc.weatherwise.domain.model.ForecastInfo
import com.oktaygenc.weatherwise.domain.model.Location
import com.oktaygenc.weatherwise.domain.model.Weather
import com.oktaygenc.weatherwise.domain.model.WeatherCondition
import com.oktaygenc.weatherwise.domain.model.WeatherInfo
import com.oktaygenc.weatherwise.data.remote.dto.WeatherResponseDto
import com.oktaygenc.weatherwise.utils.Constants
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
            weatherIcon = weatherDescription?.icon,
            timestamp = System.currentTimeMillis(),
            feelsLike = forecast?.main?.feelsLike ?: 0.0,
            tempMin = forecast?.main?.tempMin ?: 0.0,
            tempMax = forecast?.main?.tempMax ?: 0.0,
            pressure = forecast?.main?.pressure ?: 0,
            humidity = forecast?.main?.humidity ?: 0,
            forecasts = this.forecasts,
            dateText = forecast?.dateText ?: ""
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
                feelsLike = this.feelsLike,
                tempMin = this.tempMin,
                tempMax = this.tempMax,
                pressure = this.pressure,
                humidity = this.humidity,
                condition = WeatherCondition(
                    description = this.weatherDescription ?: "Unknown",
                    iconUrl = Constants.getIconUrl(this.weatherIcon),
                    timestamp = this.timestamp,
                    dateText = Constants.formatTimestamp(this.timestamp)
                )
            ),
            forecasts = forecasts.map { forecast ->
                ForecastInfo(
                    timestamp = forecast.timestamp,
                    temperature = forecast.main.temperature,
                    description = forecast.weather.firstOrNull()?.description ?: "",
                    iconUrl = Constants.getIconUrl(forecast.weather.firstOrNull()?.icon ?: ""),
                    humidity = forecast.main.humidity,
                    feelsLike = forecast.main.feelsLike,
                    date = SimpleDateFormat(
                        "dd MMM",
                        Locale.getDefault()
                    ).format(Date(forecast.timestamp * 1000))
                )
            }
        )
    }
}