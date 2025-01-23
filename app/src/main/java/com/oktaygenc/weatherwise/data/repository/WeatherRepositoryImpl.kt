package com.oktaygenc.weatherwise.data.repository

import com.oktaygenc.weatherwise.data.local.dao.WeatherDao
import com.oktaygenc.weatherwise.data.mapper.WeatherMapper.toDomainModel
import com.oktaygenc.weatherwise.data.mapper.WeatherMapper.toEntity
import com.oktaygenc.weatherwise.data.remote.api.WeatherApiService
import com.oktaygenc.weatherwise.domain.model.WeatherInfo
import com.oktaygenc.weatherwise.domain.repository.WeatherRepository
import com.oktaygenc.weatherwise.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApiService,
    private val dao: WeatherDao
) : WeatherRepository {

    override suspend fun getWeatherForecast(city: String): Flow<NetworkResult<WeatherInfo>> = flow {
        emit(NetworkResult.Loading)

        try {
            // Check cache
            dao.getWeatherByCity(city)?.let { entity ->
                if (entity.isValid()) {
                    emit(NetworkResult.Success(entity.toDomainModel()))
                }
            }
            // Fetch from API
            val response = api.getWeatherForecast(cityName = city)
            val weatherEntity = response.toEntity()

            // Save to cache
            dao.insertWeather(weatherEntity)

            emit(NetworkResult.Success(weatherEntity.toDomainModel()))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Unknown error"))
        }
    }
}
