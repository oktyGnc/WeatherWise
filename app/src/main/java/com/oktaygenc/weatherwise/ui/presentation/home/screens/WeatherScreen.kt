package com.oktaygenc.weatherwise.ui.presentation.home.screens

import android.text.format.DateUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.oktaygenc.weatherwise.domain.model.ForecastInfo
import com.oktaygenc.weatherwise.domain.model.WeatherInfo
import com.oktaygenc.weatherwise.ui.presentation.home.viewmodel.WeatherUiState
import com.oktaygenc.weatherwise.ui.presentation.home.viewmodel.WeatherViewModel
import com.oktaygenc.weatherwise.utils.Constants

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherState by viewModel.weatherState.collectAsState()
    var location by remember { mutableStateOf("Istanbul") }

    Column(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Şehir Giriniz") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = { viewModel.getWeather(location) },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Hava Durumunu Getir")
        }

        when (weatherState) {
            is WeatherUiState.Initial -> {
                Text("Bir şehir seçin")
            }
            is WeatherUiState.Loading -> {
                CircularProgressIndicator()
            }
            is WeatherUiState.Success -> {
                val weatherInfo = (weatherState as WeatherUiState.Success).weather

                // Mevcut güncel hava durumu gösterimi
                CurrentWeatherSection(weatherInfo)

                // Forecast listesi için yeni bölüm
                ForecastSection(weatherInfo.forecasts)
            }
            is WeatherUiState.Error -> {
                Text(
                    text = (weatherState as WeatherUiState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun CurrentWeatherSection(weatherInfo: WeatherInfo) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "${weatherInfo.location.cityName}, ${weatherInfo.location.country}",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Sıcaklık: ${weatherInfo.weather.temperature.toInt()}°C",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Date: ${Constants.formatTimestamp(weatherInfo.weather.condition.timestamp)}")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        val weatherDetails = listOf(
            "Hissedilen Sıcaklık: ${weatherInfo.weather.feelsLike.toInt()}°C",
            "Minimum Sıcaklık: ${weatherInfo.weather.tempMin.toInt()}°C",
            "Maksimum Sıcaklık: ${weatherInfo.weather.tempMax.toInt()}°C",
            "Basınç: ${weatherInfo.weather.pressure} hPa",
            "Nem: ${weatherInfo.weather.humidity}%",
            "Tarih: ${Constants.formatDateText(weatherInfo.weather.condition.dateText)}",
            "Durum: ${weatherInfo.weather.condition.description}"
        )

        Column {
            weatherDetails.forEach { detail ->
                Text(
                    text = detail,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ForecastSection(forecasts: List<ForecastInfo>) {
    Text(
        "5 Günlük Tahmin",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(vertical = 8.dp)
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(forecasts) { forecast ->
            ForecastItemCard(forecast)
        }
    }
}

@Composable
fun ForecastItemCard(forecast: ForecastInfo) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .padding(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                forecast.date,
                style = MaterialTheme.typography.bodyMedium
            )
            AsyncImage(
                model = forecast.iconUrl,
                contentDescription = forecast.description,
                modifier = Modifier.size(50.dp)
            )
            Text(
                "${forecast.temperature.toInt()}°C",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("Date: ${Constants.formatTimestamp(forecast.timestamp)}")
            Text(
                forecast.description,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}