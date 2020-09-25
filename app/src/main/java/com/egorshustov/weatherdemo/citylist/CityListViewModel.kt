package com.egorshustov.weatherdemo.citylist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egorshustov.weatherdemo.data.source.WeatherRepository
import kotlinx.coroutines.launch

class CityListViewModel @ViewModelInject constructor(
    weatherRepository: WeatherRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            weatherRepository.requestCurrentAndDailyWeatherByCoordinates(59.89, 30.26)
        }
    }
}