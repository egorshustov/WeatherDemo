package com.egorshustov.weatherdemo.data.source

import com.egorshustov.weatherdemo.data.CityAndCurrentWeather
import com.egorshustov.weatherdemo.data.source.local.CitiesLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCitiesRepository @Inject constructor(
    private val citiesLocalDataSource: CitiesLocalDataSource
) : CitiesRepository {

    override fun getCitiesAndCurrentWeather(): Flow<List<CityAndCurrentWeather>> =
        citiesLocalDataSource.getCitiesAndCurrentWeather()

    override fun getCityAndCurrentWeather(cityId: Long): Flow<CityAndCurrentWeather> =
        citiesLocalDataSource.getCityAndCurrentWeather(cityId)

}