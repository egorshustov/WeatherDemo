package com.egorshustov.weatherdemo.util

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import timber.log.Timber

fun NavController.safeNavigate(navDirections: NavDirections) {
    try {
        navigate(navDirections)
    } catch (e: Exception) {
        Timber.e(e)
    }
}