package com.oktaygenc.weatherwise.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather_table WHERE cityName = :cityName ORDER BY timestamp DESC LIMIT 1")
    suspend fun getWeatherByCity(cityName: String): WeatherEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)
}