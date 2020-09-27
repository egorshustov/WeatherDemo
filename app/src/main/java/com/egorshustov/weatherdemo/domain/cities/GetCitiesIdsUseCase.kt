package com.egorshustov.weatherdemo.domain.cities

import com.egorshustov.weatherdemo.data.source.CitiesRepository
import com.egorshustov.weatherdemo.util.composeString
import javax.inject.Inject

class GetCitiesIdsUseCase @Inject constructor(private val citiesRepository: CitiesRepository) {

    suspend operator fun invoke(): String = citiesRepository.getCitiesIds().composeString()
}