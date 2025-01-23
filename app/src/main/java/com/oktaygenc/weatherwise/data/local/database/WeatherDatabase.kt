package com.oktaygenc.weatherwise.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.oktaygenc.weatherwise.data.local.entity.WeatherEntity
import com.oktaygenc.weatherwise.data.local.converter.ForecastTypeConverter
import com.oktaygenc.weatherwise.data.local.dao.WeatherDao

@Database(entities = [WeatherEntity::class], version = 1)
@TypeConverters(ForecastTypeConverter::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}