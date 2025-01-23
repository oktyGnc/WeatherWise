package com.oktaygenc.weatherwise.data.remote.api

import com.oktaygenc.weatherwise.data.remote.dto.WeatherResponseDto
import com.oktaygenc.weatherwise.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = Constants.API_KEY,
        @Query("units") units: String = "metric"
    ): WeatherResponseDto
}