package com.egorshustov.weatherdemo.data.source

import com.egorshustov.weatherdemo.data.DailyWeather
import com.egorshustov.weatherdemo.data.source.local.CitiesLocalDataSource
import com.egorshustov.weatherdemo.data.source.local.WeatherLocalDataSource
import com.egorshustov.weatherdemo.data.source.remote.Result
import com.egorshustov.weatherdemo.data.source.remote.WeatherRemoteDataSource
import com.egorshustov.weatherdemo.util.toCityEntity
import com.egorshustov.weatherdemo.util.toCurrentWeatherEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultWeatherRepository @Inject constructor(
    private val citiesLocalDataSource: CitiesLocalDataSource,
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : WeatherRepository {

    override fun getDailyWeatherList(cityId: Long): Flow<List<DailyWeather>> =
        weatherLocalDataSource.getDailyWeatherList(cityId)

    override suspend fun requestCurrentWeatherForCitiesByIds(
        idsString: String,
        measureUnits: String,
        responseLanguage: String,
        weatherApiKey: String
    ): Result<Unit> = withContext(ioDispatcher) {
        when (val currentWeatherForCitiesResult =
            weatherRemoteDataSource.getCurrentWeatherForCitiesByIds(
                idsString,
                measureUnits,
                responseLanguage,
                weatherApiKey
            )) {
            is Result.Success -> {
                val currentWeatherList =
                    currentWeatherForCitiesResult.data.cityAndWeatherList?.map { it.toCurrentWeatherEntity() }
                if (!currentWeatherList.isNullOrEmpty()) {
                    weatherLocalDataSource.saveCurrentWeatherList(currentWeatherList)
                }
                Result.Success(Unit)
            }
            is Result.Error -> {
                Result.Error(currentWeatherForCitiesResult.exception)
            }
        }
    }

    override suspend fun requestCurrentWeatherForCityByName(
        cityName: String,
        measureUnits: String,
        responseLanguage: String,
        weatherApiKey: String
    ): Result<Unit> = withContext(ioDispatcher) {
        when (val currentWeatherForCityResult =
            weatherRemoteDataSource.getCurrentWeatherForCityByName(
                cityName,
                measureUnits,
                responseLanguage,
                weatherApiKey
            )) {
            is Result.Success -> {
                val city = currentWeatherForCityResult.data.toCityEntity()
                val currentWeather = currentWeatherForCityResult.data.toCurrentWeatherEntity()
                citiesLocalDataSource.saveCity(city)
                weatherLocalDataSource.saveCurrentWeather(currentWeather)
                Result.Success(Unit)
            }
            is Result.Error -> {
                Result.Error(currentWeatherForCityResult.exception)
            }
        }
    }

    override suspend fun requestCurrentAndDailyWeatherByCoordinates(
        cityId: Long,
        latitude: Double,
        longitude: Double,
        excludeFields: String,
        measureUnits: String,
        responseLanguage: String,
        weatherApiKey: String
    ): Result<Unit> = withContext(ioDispatcher) {
        when (val currentAndDailyWeatherResult =
            weatherRemoteDataSource.getCurrentAndDailyWeatherByCoordinates(
                latitude,
                longitude,
                excludeFields,
                measureUnits,
                responseLanguage,
                weatherApiKey
            )) {
            is Result.Success -> {
                val result = currentAndDailyWeatherResult.data
                Result.Success(Unit)
            }
            is Result.Error -> {
                Result.Error(currentAndDailyWeatherResult.exception)
            }
        }
    }
}

