package com.egorshustov.weatherdemo.util

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber

enum class MessageLength { SHORT, LONG }

fun Context.showMessage(message: String, duration: MessageLength = MessageLength.LONG) {
    val toastDuration = if (duration == MessageLength.LONG) {
        Toast.LENGTH_LONG
    } else {
        Toast.LENGTH_SHORT
    }
    Toast.makeText(this, message, toastDuration).show()
}

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

fun List<Long>.composeString(): String {
    var result = ""
    forEach { result += "$it," }
    return result.dropLast(1)
}