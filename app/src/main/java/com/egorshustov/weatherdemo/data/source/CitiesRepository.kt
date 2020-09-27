package com.egorshustov.weatherdemo.data.source

import com.egorshustov.weatherdemo.data.CityAndCurrentWeather
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {

    suspend fun getCitiesIds(): List<Long>

    fun getCitiesAndCurrentWeather(): Flow<List<CityAndCurrentWeather>>

    fun getCityAndCurrentWeather(cityId: Long): Flow<CityAndCurrentWeather>
}