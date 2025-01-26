package com.oktaygenc.weatherwise.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Constants {
    const val BASE_URL = "https://api.openweathermap.org/"

    fun getIconUrl(icon: String?): String {
        return "https://openweathermap.org/img/wn/${icon}@2x.png"
    }

    fun formatTimestamp(timestamp: Long): String {
        val correctedTimestamp = if (timestamp < 10_000_000_000L) timestamp * 1000 else timestamp

        val date = Date(correctedTimestamp)
        val formatter = SimpleDateFormat("dd MMMM HH:mm", Locale.getDefault())
        return formatter.format(date)
    }
}
