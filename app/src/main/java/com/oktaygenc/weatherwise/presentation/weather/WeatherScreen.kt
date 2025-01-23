package com.oktaygenc.weatherwise.presentation.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
        // Search Bar
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

        // Weather State Display
        when (weatherState) {
            is WeatherUiState.Initial -> {
                Text("Bir şehir seçin")
            }
            is WeatherUiState.Loading -> {
                CircularProgressIndicator()
            }
            is WeatherUiState.Success -> {
                val weatherInfo = (weatherState as WeatherUiState.Success).weather
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
                    Text(
                        text = "Hissedilen Sıcaklık: ${weatherInfo.weather.feelsLike.toInt()}°C",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Minimum Sıcaklık: ${weatherInfo.weather.tempMin.toInt()}°C",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Maksimum Sıcaklık: ${weatherInfo.weather.tempMax.toInt()}°C",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Basınç: ${weatherInfo.weather.pressure} hPa",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Nem: ${weatherInfo.weather.humidity}%",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tarih: ${weatherInfo.weather.condition.formattedDate}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Durum: ${weatherInfo.weather.condition.description}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AsyncImage(
                        model = weatherInfo.weather.condition.iconUrl,
                        contentDescription = null,
                        modifier = Modifier.height(120.dp))
                }
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