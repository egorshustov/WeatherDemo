package com.egorshustov.weatherdemo.domain.weather

import com.egorshustov.weatherdemo.data.source.WeatherRepository
import com.egorshustov.weatherdemo.data.source.remote.Result
import com.egorshustov.weatherdemo.util.EXCLUDE_FIELDS
import com.egorshustov.weatherdemo.util.METRIC_MEASURE_UNITS
import com.egorshustov.weatherdemo.util.RUSSIAN_LANGUAGE
import com.egorshustov.weatherdemo.util.WEATHER_API_KEY
import javax.inject.Inject

class RequestCurrentAndDailyWeatherByCoordinatesUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
        excludeFields: String = EXCLUDE_FIELDS,
        measureUnits: String = METRIC_MEASURE_UNITS,
        responseLanguage: String = RUSSIAN_LANGUAGE,
        weatherApiKey: String = WEATHER_API_KEY
    ): Result<Unit> = weatherRepository.requestCurrentAndDailyWeatherByCoordinates(
        latitude, longitude, excludeFields, measureUnits, responseLanguage, weatherApiKey
    )
}