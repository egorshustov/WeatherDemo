package com.egorshustov.weatherdemo.data.source.local

import com.egorshustov.weatherdemo.data.City
import com.egorshustov.weatherdemo.data.CityAndCurrentWeather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CitiesRoomDataSource @Inject constructor(
    private val citiesDao: CitiesDao,
    private val ioDispatcher: CoroutineDispatcher
) : CitiesLocalDataSource {

    override fun getCitiesAndCurrentWeather(): Flow<List<CityAndCurrentWeather>> =
        citiesDao.getCitiesAndCurrentWeather()

    override fun getCityAndCurrentWeather(cityId: Long): Flow<CityAndCurrentWeather> =
        citiesDao.getCityAndCurrentWeather(cityId)

    override suspend fun saveCity(city: City) =
        withContext(ioDispatcher) { citiesDao.insertCity(city) }
}