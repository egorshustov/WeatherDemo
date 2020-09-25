package com.egorshustov.weatherdemo.data.source.local

import com.egorshustov.weatherdemo.data.City
import com.egorshustov.weatherdemo.data.CityAndCurrentWeather
import kotlinx.coroutines.flow.Flow

interface CitiesLocalDataSource {

    fun getCitiesAndCurrentWeather(): Flow<List<CityAndCurrentWeather>>

    fun getCityAndCurrentWeather(cityId: Long): Flow<CityAndCurrentWeather>

    suspend fun saveCity(city: City)
}