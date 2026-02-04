package com.example.passwordlessauth.analytics

import timber.log.Timber

object AnalyticsLogger {

    fun logOtpGenerated() {
        Timber.d("OTP generated")
    }

    fun logOtpSuccess() {
        Timber.d("OTP validation success")
    }

    fun logOtpFailure() {
        Timber.d("OTP validation failure")
    }

    fun logLogout() {
        Timber.d("User logged out")
    }
}
