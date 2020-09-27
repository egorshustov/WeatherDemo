package com.egorshustov.weatherdemo.citylist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egorshustov.weatherdemo.domain.weather.RequestCurrentAndDailyWeatherByCoordinatesUseCase
import kotlinx.coroutines.launch

class CityListViewModel @ViewModelInject constructor(
    requestCurrentAndDailyWeatherByCoordinatesUseCase: RequestCurrentAndDailyWeatherByCoordinatesUseCase
) : ViewModel() {
    init {
        viewModelScope.launch {
            requestCurrentAndDailyWeatherByCoordinatesUseCase(59.89, 30.26)
        }
    }
}