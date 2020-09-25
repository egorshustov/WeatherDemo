package com.egorshustov.weatherdemo.data.source.remote

import com.egorshustov.weatherdemo.data.source.remote.getcurrentanddailyweatherbycoordinates.CurrentAndDailyWeatherResponse
import com.egorshustov.weatherdemo.data.source.remote.getcurrentweatherforcitiesbyids.CurrentWeatherListResponse
import com.egorshustov.weatherdemo.data.source.remote.getcurrentweatherforcitybyname.CurrentWeatherForCityResponse

interface WeatherRemoteDataSource {

    suspend fun getCurrentWeatherForCitiesByIds(
        idsString: String,
        measureUnits: String,
        responseLanguage: String,
        weatherApiKey: String
    ): Result<CurrentWeatherListResponse>

    suspend fun getCurrentWeatherForCityByName(
        cityName: String,
        measureUnits: String,
        responseLanguage: String,
        weatherApiKey: String
    ): Result<CurrentWeatherForCityResponse>

    suspend fun getCurrentAndDailyWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        excludeFields: String,
        measureUnits: String,
        responseLanguage: String,
        weatherApiKey: String
    ): Result<CurrentAndDailyWeatherResponse>
}