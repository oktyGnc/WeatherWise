package com.oktaygenc.weatherwise.di

import com.oktaygenc.weatherwise.data.local.dao.WeatherDao
import com.oktaygenc.weatherwise.data.remote.api.WeatherApiService
import com.oktaygenc.weatherwise.data.repository.WeatherRepositoryImpl
import com.oktaygenc.weatherwise.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
     @Provides
     @Singleton
     fun provideWeatherRepository(
         weatherApi: WeatherApiService,
         weatherDao: WeatherDao
   ): WeatherRepository {
       return WeatherRepositoryImpl(weatherApi, weatherDao) }
} 