package com.egorshustov.weatherdemo.data

import androidx.room.Embedded
import androidx.room.Relation

data class CityAndCurrentWeather(
    @Embedded
    val city: City,
    @Relation(
        parentColumn = "id",
        entityColumn = "city_id"
    )
    val currentWeather: CurrentWeather
)