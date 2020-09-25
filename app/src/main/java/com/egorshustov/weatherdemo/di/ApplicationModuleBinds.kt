package com.egorshustov.weatherdemo.di

import com.egorshustov.weatherdemo.data.source.CitiesRepository
import com.egorshustov.weatherdemo.data.source.DefaultCitiesRepository
import com.egorshustov.weatherdemo.data.source.DefaultWeatherRepository
import com.egorshustov.weatherdemo.data.source.WeatherRepository
import com.egorshustov.weatherdemo.data.source.local.CitiesLocalDataSource
import com.egorshustov.weatherdemo.data.source.local.CitiesRoomDataSource
import com.egorshustov.weatherdemo.data.source.local.WeatherLocalDataSource
import com.egorshustov.weatherdemo.data.source.local.WeatherRoomDataSource
import com.egorshustov.weatherdemo.data.source.remote.WeatherRemoteDataSource
import com.egorshustov.weatherdemo.data.source.remote.WeatherRetrofitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ApplicationModuleBinds {

    @Binds
    abstract fun bindCitiesLocalDataSource(
        citiesRoomDataSource: CitiesRoomDataSource
    ): CitiesLocalDataSource

    @Binds
    abstract fun bindWeatherLocalDataSource(
        weatherRoomDataSource: WeatherRoomDataSource
    ): WeatherLocalDataSource

    @Binds
    abstract fun bindWeatherRemoteDataSource(
        weatherRetrofitDataSource: WeatherRetrofitDataSource
    ): WeatherRemoteDataSource

    @Binds
    abstract fun bindCitiesRepository(
        defaultCitiesRepository: DefaultCitiesRepository
    ): CitiesRepository

    @Binds
    abstract fun bindWeatherRepository(
        defaultWeatherRepository: DefaultWeatherRepository
    ): WeatherRepository
}