package com.oktaygenc.weatherwise.ui.presentation.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.oktaygenc.weatherwise.ui.presentation.home.components.CurrentWeatherSection
import com.oktaygenc.weatherwise.ui.presentation.home.components.ErrorCard
import com.oktaygenc.weatherwise.ui.presentation.home.components.ForecastSection
import com.oktaygenc.weatherwise.ui.presentation.home.components.SearchSection
import com.oktaygenc.weatherwise.ui.presentation.home.components.WeatherDetailsSection
import com.oktaygenc.weatherwise.ui.presentation.home.viewmodel.WeatherUiState
import com.oktaygenc.weatherwise.ui.presentation.home.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    val weatherState by viewModel.weatherState.collectAsState()
    var location by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2196F3), Color(0xFF64B5F6)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchSection(location,
                onLocationChange = { location = it },
                onSearch = { viewModel.getWeather(location) })

            when (weatherState) {
                is WeatherUiState.Initial -> {
                    Text(
                        "Search for a city",
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }

                is WeatherUiState.Loading -> {
                    CircularProgressIndicator(
                        color = Color.White, modifier = Modifier.padding(32.dp)
                    )
                }

                is WeatherUiState.Success -> {
                    val weatherInfo = (weatherState as WeatherUiState.Success).weather
                    CurrentWeatherSection(weatherInfo)
                    WeatherDetailsSection(weatherInfo)
                    ForecastSection(forecasts = weatherInfo.forecasts)
                }

                is WeatherUiState.Error -> {
                    ErrorCard(
                        message = "I think there's a problem"
                    )
                }
            }
        }
    }
}