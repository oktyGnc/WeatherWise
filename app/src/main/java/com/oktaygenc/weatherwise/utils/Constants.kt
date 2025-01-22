package com.oktaygenc.weatherwise.utils

object Constants {
    const val BASE_URL = "https://api.openweathermap.org/"
    const val API_KEY = "42634048b6d6c1e9f3e0afac9f598170"

     fun getIconUrl(icon: String?): String {
        return "https://openweathermap.org/img/wn/${icon}@2x.png"
    }
}