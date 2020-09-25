package com.egorshustov.weatherdemo.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.egorshustov.weatherdemo.data.City
import com.egorshustov.weatherdemo.data.CurrentWeather
import com.egorshustov.weatherdemo.data.DailyWeather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [City::class, CurrentWeather::class, DailyWeather::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDemoDatabase : RoomDatabase() {

    abstract fun citiesDao(): CitiesDao
    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDemoDatabase? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(WeatherDemoDatabase::class.java) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): WeatherDemoDatabase = Room.databaseBuilder(
            context.applicationContext,
            WeatherDemoDatabase::class.java,
            "WeatherDemo.db"
        ).addCallback(object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    getInstance(context).citiesDao().insertCities(PREPOPULATE_CITIES)
                }
            }
        }).build()

        private val PREPOPULATE_CITIES = listOf(
            City(
                524901,
                55.75,
                37.62,
                "Москва",
                "RU",
                10800
            ),
            City(
                498817,
                59.89,
                30.26,
                "Санкт-Петербург",
                "RU",
                10800
            )
        )
    }
}