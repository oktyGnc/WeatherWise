package com.oktaygenc.weatherwise.domain.usecase

import com.oktaygenc.weatherwise.domain.model.WeatherInfo
import com.oktaygenc.weatherwise.domain.repository.WeatherRepository
import com.oktaygenc.weatherwise.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : BaseUseCase<GetWeatherUseCase.Params, Flow<NetworkResult<WeatherInfo>>> {

    override suspend fun invoke(params: Params): Flow<NetworkResult<WeatherInfo>> {
        return weatherRepository.getWeatherForecast(params.location)
    }

    data class Params(
        val location: String,
    )
}

