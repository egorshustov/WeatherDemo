package com.egorshustov.weatherdemo.domain.cities

import com.egorshustov.weatherdemo.data.CityAndCurrentWeather
import com.egorshustov.weatherdemo.data.source.CitiesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCitiesAndCurrentWeatherUseCase @Inject constructor(private val citiesRepository: CitiesRepository) {

    operator fun invoke(): Flow<List<CityAndCurrentWeather>> =
        citiesRepository.getCitiesAndCurrentWeather()
}