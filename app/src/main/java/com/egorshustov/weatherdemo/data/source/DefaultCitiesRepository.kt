package com.egorshustov.weatherdemo.data.source

import com.egorshustov.weatherdemo.data.City
import com.egorshustov.weatherdemo.data.CityAndCurrentWeather
import com.egorshustov.weatherdemo.data.source.local.CitiesLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCitiesRepository @Inject constructor(
    private val citiesLocalDataSource: CitiesLocalDataSource
) : CitiesRepository {

    override suspend fun getCity(cityId: Long): City = citiesLocalDataSource.getCity(cityId)

    override suspend fun getCitiesIds(): List<Long> = citiesLocalDataSource.getCitiesIds()

    override fun getCitiesAndCurrentWeather(): Flow<List<CityAndCurrentWeather>> =
        citiesLocalDataSource.getCitiesAndCurrentWeather()
}