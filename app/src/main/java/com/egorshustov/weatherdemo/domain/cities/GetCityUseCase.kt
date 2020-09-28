package com.egorshustov.weatherdemo.domain.cities

import com.egorshustov.weatherdemo.data.City
import com.egorshustov.weatherdemo.data.source.CitiesRepository
import javax.inject.Inject

class GetCityUseCase @Inject constructor(private val citiesRepository: CitiesRepository) {

    suspend operator fun invoke(cityId: Long): City = citiesRepository.getCity(cityId)
}