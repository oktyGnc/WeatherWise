package com.oktaygenc.weatherwise.ui.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oktaygenc.weatherwise.domain.model.WeatherInfo
import com.oktaygenc.weatherwise.domain.usecase.GetWeatherUseCase
import com.oktaygenc.weatherwise.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherUiState>(WeatherUiState.Initial)
    val weatherState: StateFlow<WeatherUiState> = _weatherState

    fun getWeather(location: String) {
        viewModelScope.launch {
            _weatherState.value = WeatherUiState.Loading

            getWeatherUseCase(GetWeatherUseCase.Params(location)).collect { result ->
                _weatherState.value = when (result) {
                    is NetworkResult.Success -> WeatherUiState.Success(result.data)
                    is NetworkResult.Error -> WeatherUiState.Error(result.message)
                    is NetworkResult.Loading -> WeatherUiState.Loading
                }
            }
        }
    }
}

sealed class WeatherUiState {
    data object Initial : WeatherUiState()
    data object Loading : WeatherUiState()
    data class Success(val weather: WeatherInfo) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}