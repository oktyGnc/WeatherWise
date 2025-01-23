package com.oktaygenc.weatherwise.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val cityName: String?,
    val country: String?,
    val temperature: Double?,
    val weatherDescription: String?,
    val weatherIcon: String?,
    val timestamp: Long = System.currentTimeMillis(),
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int,
) {
    fun isValid(): Boolean {
        return cityName != null && temperature != null && weatherDescription != null && timestamp > 0
    }
}