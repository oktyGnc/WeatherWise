package com.oktaygenc.weatherwise.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oktaygenc.weatherwise.data.remote.dto.ForecastDto

class ForecastTypeConverter {
    @TypeConverter
    fun fromForecastList(forecasts: List<ForecastDto>?): String? {
        return Gson().toJson(forecasts)
    }

    @TypeConverter
    fun toForecastList(forecastString: String?): List<ForecastDto>? {
        if (forecastString == null) return null
        val type = object : TypeToken<List<ForecastDto>>() {}.type
        return Gson().fromJson(forecastString, type)
    }
}