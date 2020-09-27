package com.egorshustov.weatherdemo.data.source.local

import androidx.room.*
import com.egorshustov.weatherdemo.data.CurrentWeather
import com.egorshustov.weatherdemo.data.DailyWeather
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("select * from daily_weather where city_id = :cityId order by date_time_unix_seconds")
    fun getDailyWeatherList(cityId: Long): Flow<List<DailyWeather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(currentWeather: CurrentWeather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeatherList(currentWeatherList: List<CurrentWeather>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyWeatherList(dailyWeatherList: List<DailyWeather>)

    @Query("DELETE from daily_weather where city_id = :cityId")
    suspend fun deleteDailyWeatherFrom(cityId: Long)

    @Transaction
    suspend fun replaceDailyWeatherFor(cityId: Long, dailyWeatherList: List<DailyWeather>) {
        deleteDailyWeatherFrom(cityId)
        insertDailyWeatherList(dailyWeatherList)
    }
}