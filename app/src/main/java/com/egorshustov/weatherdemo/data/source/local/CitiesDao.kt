package com.egorshustov.weatherdemo.data.source.local

import androidx.room.*
import com.egorshustov.weatherdemo.data.City
import com.egorshustov.weatherdemo.data.CityAndCurrentWeather
import kotlinx.coroutines.flow.Flow

@Dao
interface CitiesDao {
    @Query("select id from cities")
    suspend fun getCitiesIds(): List<Long>

    @Transaction
    @Query("select * from cities order by name")
    fun getCitiesAndCurrentWeather(): Flow<List<CityAndCurrentWeather>>

    @Transaction
    @Query("select * from cities where id = :cityId")
    fun getCityAndCurrentWeather(cityId: Long): Flow<CityAndCurrentWeather>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCity(city: City)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCities(cities: List<City>)
}