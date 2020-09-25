package com.egorshustov.weatherdemo.data.source.local

import com.egorshustov.weatherdemo.data.CurrentWeather
import com.egorshustov.weatherdemo.data.DailyWeather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRoomDataSource @Inject constructor(
    private val weatherDao: WeatherDao,
    private val ioDispatcher: CoroutineDispatcher
) : WeatherLocalDataSource {

    override fun getDailyWeatherList(cityId: Long): Flow<List<DailyWeather>> =
        weatherDao.getDailyWeatherList(cityId)

    override suspend fun saveCurrentWeather(currentWeather: CurrentWeather) =
        withContext(ioDispatcher) { weatherDao.insertCurrentWeather(currentWeather) }

    override suspend fun saveDailyWeatherList(cityId: Long, dailyWeatherList: List<DailyWeather>) =
        withContext(ioDispatcher) { weatherDao.replaceDailyWeatherFor(cityId, dailyWeatherList) }
}