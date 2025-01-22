package com.oktaygenc.weatherwise.domain.repository

import com.oktaygenc.weatherwise.domain.model.WeatherInfo
import com.oktaygenc.weatherwise.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherForecast(city: String): Flow<NetworkResult<WeatherInfo>>
}