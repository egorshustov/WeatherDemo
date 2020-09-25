package com.egorshustov.weatherdemo.util

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber

fun NavController.safeNavigate(navDirections: NavDirections) {
    try {
        navigate(navDirections)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

suspend inline fun <reified T> Retrofit.convertResponseBody(responseBody: ResponseBody?): T? =
    withContext(Dispatchers.Default) {
        responseBody ?: return@withContext null
        val converter: Converter<ResponseBody, T> =
            responseBodyConverter(T::class.java, emptyArray())
        converter.convert(responseBody)
    }