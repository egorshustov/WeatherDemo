package com.egorshustov.weatherdemo.cityinfo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.egorshustov.weatherdemo.data.CityAndCurrentWeather
import com.egorshustov.weatherdemo.data.DailyWeather
import com.egorshustov.weatherdemo.data.source.remote.Result
import com.egorshustov.weatherdemo.domain.cities.GetCitiesAndCurrentWeatherUseCase
import com.egorshustov.weatherdemo.domain.cities.GetCityUseCase
import com.egorshustov.weatherdemo.domain.weather.GetDailyWeatherListUseCase
import com.egorshustov.weatherdemo.domain.weather.RequestCurrentAndDailyWeatherByCoordinatesUseCase
import com.egorshustov.weatherdemo.util.Event
import com.egorshustov.weatherdemo.util.NO_VALUE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CityInfoViewModel @ViewModelInject constructor(
    val getCitiesAndCurrentWeatherUseCase: GetCitiesAndCurrentWeatherUseCase,
    val getDailyWeatherListUseCase: GetDailyWeatherListUseCase,
    val getCityUseCase: GetCityUseCase,
    val requestCurrentAndDailyWeatherByCoordinatesUseCase: RequestCurrentAndDailyWeatherByCoordinatesUseCase
) : ViewModel() {

    private var cityId: Long = NO_VALUE

    val cityAndCurrentWeather: LiveData<CityAndCurrentWeather> =
        getCitiesAndCurrentWeatherUseCase()
            .flowOn(Dispatchers.IO)
            .map { it.find { it.city.id == cityId } }
            .filterNotNull()
            .flowOn(Dispatchers.Default)
            .asLiveData()

    val dailyWeatherList: LiveData<List<DailyWeather>> =
        getDailyWeatherListUseCase()
            .flowOn(Dispatchers.IO)
            .map { it.filter { it.cityId == cityId } }
            .filterNotNull()
            .flowOn(Dispatchers.Default)
            .asLiveData()

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = _message

    fun onCityIdObtained(cityId: Long) {
        this.cityId = cityId
        requestCurrentAndDailyWeather(cityId)
    }

    fun onNetworkRestored() {
        val currentCityId = cityId
        if (currentCityId != NO_VALUE) requestCurrentAndDailyWeather(currentCityId)
    }

    private fun requestCurrentAndDailyWeather(cityId: Long) {
        viewModelScope.launch {
            val city = getCityUseCase(cityId)
            val requestResult = requestCurrentAndDailyWeatherByCoordinatesUseCase(
                cityId,
                city.latitude,
                city.longitude
            )
            if (requestResult is Result.Error) _message.value = Event(requestResult.getString())
        }
    }
}