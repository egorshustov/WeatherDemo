package com.egorshustov.weatherdemo.addcity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egorshustov.weatherdemo.data.source.remote.Result
import com.egorshustov.weatherdemo.domain.weather.RequestCurrentWeatherForCityByNameUseCase
import com.egorshustov.weatherdemo.util.Event
import kotlinx.coroutines.launch

class AddCityViewModel @ViewModelInject constructor(
    val requestCurrentWeatherForCityByNameUseCase: RequestCurrentWeatherForCityByNameUseCase
) : ViewModel() {

    private val _requestResult = MutableLiveData<Event<Result<Unit>>>()
    val requestResult: LiveData<Event<Result<Unit>>> = _requestResult

    fun onCityEntered(cityName: String) {
        viewModelScope.launch {
            val requestResult = requestCurrentWeatherForCityByNameUseCase(cityName)
            _requestResult.value = Event(requestResult)
        }
    }
}