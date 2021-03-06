package com.egorshustov.weatherdemo.domain.weather

import com.egorshustov.weatherdemo.data.source.WeatherRepository
import com.egorshustov.weatherdemo.data.source.remote.Result
import com.egorshustov.weatherdemo.util.METRIC_MEASURE_UNITS
import com.egorshustov.weatherdemo.util.RUSSIAN_LANGUAGE
import com.egorshustov.weatherdemo.util.WEATHER_API_KEY
import javax.inject.Inject

class RequestCurrentWeatherForCitiesByIdsUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(
        idsString: String,
        measureUnits: String = METRIC_MEASURE_UNITS,
        responseLanguage: String = RUSSIAN_LANGUAGE,
        weatherApiKey: String = WEATHER_API_KEY
    ): Result<Unit> = weatherRepository.requestCurrentWeatherForCitiesByIds(
        idsString,
        measureUnits,
        responseLanguage,
        weatherApiKey
    )
}