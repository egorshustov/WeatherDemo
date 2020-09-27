package com.egorshustov.weatherdemo.domain.weather

import com.egorshustov.weatherdemo.data.DailyWeather
import com.egorshustov.weatherdemo.data.source.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDailyWeatherListUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    operator fun invoke(cityId: Long): Flow<List<DailyWeather>> =
        weatherRepository.getDailyWeatherList(cityId)
}