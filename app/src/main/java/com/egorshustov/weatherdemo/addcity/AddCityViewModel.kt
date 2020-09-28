package com.egorshustov.weatherdemo.addcity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egorshustov.weatherdemo.domain.weather.RequestCurrentWeatherForCityByNameUseCase
import kotlinx.coroutines.launch

class AddCityViewModel @ViewModelInject constructor(
    requestCurrentWeatherForCityByNameUseCase: RequestCurrentWeatherForCityByNameUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            requestCurrentWeatherForCityByNameUseCase("Курган")
        }
    }
}