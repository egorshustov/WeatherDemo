package com.egorshustov.weatherdemo.data.source

import com.egorshustov.weatherdemo.data.DailyWeather
import com.egorshustov.weatherdemo.data.source.remote.Result
import com.egorshustov.weatherdemo.util.EXCLUDE_FIELDS
import com.egorshustov.weatherdemo.util.METRIC_MEASURE_UNITS
import com.egorshustov.weatherdemo.util.RUSSIAN_LANGUAGE
import com.egorshustov.weatherdemo.util.WEATHER_API_KEY
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getDailyWeatherList(cityId: Long): Flow<List<DailyWeather>>

    suspend fun requestCurrentWeatherForCitiesByIds(
        idsString: String,
        measureUnits: String = METRIC_MEASURE_UNITS,
        responseLanguage: String = RUSSIAN_LANGUAGE,
        weatherApiKey: String = WEATHER_API_KEY
    ): Result<Unit>

    suspend fun requestCurrentWeatherForCityByName(
        cityName: String,
        measureUnits: String = METRIC_MEASURE_UNITS,
        responseLanguage: String = RUSSIAN_LANGUAGE,
        weatherApiKey: String = WEATHER_API_KEY
    ): Result<Unit>

    suspend fun requestCurrentAndDailyWeatherByCoordinates(
        cityId: Long,
        latitude: Double,
        longitude: Double,
        excludeFields: String = EXCLUDE_FIELDS,
        measureUnits: String = METRIC_MEASURE_UNITS,
        responseLanguage: String = RUSSIAN_LANGUAGE,
        weatherApiKey: String = WEATHER_API_KEY
    ): Result<Unit>
}