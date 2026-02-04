package com.example.passwordlessauth.analytics

import timber.log.Timber
object AnalyticsLogger {

    private const val TAG = "AUTH"

    fun logOtpGenerated() {
        Timber.tag(TAG).d("OTP generated")
    }

    fun logOtpSuccess() {
        Timber.tag(TAG).d("OTP verified successfully")
    }

    fun logOtpFailure() {
        Timber.tag(TAG).d("OTP verification failed")
    }

    fun logLogout() {
        Timber.tag(TAG).d("User logged out")
    }
}
