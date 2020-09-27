package com.egorshustov.weatherdemo.util

import com.egorshustov.weatherdemo.data.CurrentWeather
import com.egorshustov.weatherdemo.data.source.remote.getcurrentweatherforcitiesbyids.CityAndWeatherResponse

fun CityAndWeatherResponse.toCurrentWeatherEntity(): CurrentWeather = CurrentWeather(
    cityId = cityId ?: NO_VALUE,
    dateTimeUnixSeconds = dateTimeUnixSeconds ?: NO_VALUE,
    description = weather?.getOrNull(0)?.description.orEmpty(),
    iconUrl = if (weather?.getOrNull(0)?.iconUrlLastSegment.isNullOrEmpty()) {
        ""
    } else {
        WEATHER_ICON_URL_FIRST_PART + weather?.getOrNull(0)?.iconUrlLastSegment + "@2x.png"
    },
    sunriseUnixSeconds = sys?.sunriseUnixSeconds ?: NO_VALUE,
    sunsetUnixSeconds = sys?.sunsetUnixSeconds ?: NO_VALUE,
    pressure = main?.pressure ?: NO_VALUE.toInt(),
    humidity = main?.humidity ?: NO_VALUE.toInt(),
    visibility = visibility ?: NO_VALUE.toInt(),
    windSpeed = wind?.speed ?: NO_VALUE.toDouble(),
    windDegree = wind?.degree ?: NO_VALUE.toDouble(),
    temperature = main?.temperature ?: NO_VALUE.toDouble(),
    feelsLikeTemperature = main?.feelsLikeTemperature ?: NO_VALUE.toDouble()
)