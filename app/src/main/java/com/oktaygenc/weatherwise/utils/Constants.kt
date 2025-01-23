package com.oktaygenc.weatherwise.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Constants {
    const val BASE_URL = "https://api.openweathermap.org/"
    const val API_KEY = "42634048b6d6c1e9f3e0afac9f598170"

    fun getIconUrl(icon: String?): String {
        return "https://openweathermap.org/img/wn/${icon}@2x.png"
    }

    fun formatTimestamp(timestamp: Long): String {
        val correctedTimestamp = if (timestamp < 10_000_000_000L) timestamp * 1000 else timestamp

        val date = Date(correctedTimestamp)
        val formatter = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.getDefault())
        return formatter.format(date)
    }

    fun formatDateText(dateText: String): String {
        val inputFormat = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.getDefault())  // Gelen format
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()) // Aynı formatla döndürüyoruz
        return try {
            val date = inputFormat.parse(dateText)
            outputFormat.format(date!!)
        } catch (e: ParseException) {
            "Geçersiz tarih"
        }
    }
}
