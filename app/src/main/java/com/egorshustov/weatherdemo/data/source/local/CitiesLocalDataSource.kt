package com.egorshustov.weatherdemo.data.source.local

import com.egorshustov.weatherdemo.data.City
import com.egorshustov.weatherdemo.data.CityAndCurrentWeather
import kotlinx.coroutines.flow.Flow

interface CitiesLocalDataSource {

    suspend fun getCity(cityId: Long): City

    suspend fun getCitiesIds(): List<Long>

    fun getCitiesAndCurrentWeather(): Flow<List<CityAndCurrentWeather>>

    suspend fun saveCity(city: City)
}