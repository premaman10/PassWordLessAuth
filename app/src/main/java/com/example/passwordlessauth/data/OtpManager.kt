package com.example.passwordlessauth.data

import android.util.Log

class OtpManager {

    private val otpStore = mutableMapOf<String, OtpData>()
    private val OTP_VALIDITY = 60_000L // 60 seconds
    private val MAX_ATTEMPTS = 3

    fun generateOtp(email: String): String {
        val otp = (100000..999999).random().toString()
        Log.d("AUTH", "OTP for $email is $otp")
        otpStore[email] = OtpData(otp, System.currentTimeMillis())
        return otp
    }

    fun validateOtp(email: String, inputOtp: String): OtpResult {
        val data = otpStore[email] ?: return OtpResult.Expired

        // Expiry check
        if (System.currentTimeMillis() - data.createdAt > OTP_VALIDITY) {
            otpStore.remove(email)
            return OtpResult.Expired
        }

        // Invalid OTP
        if (data.otp != inputOtp) {
            data.attempts++
            return if (data.attempts >= MAX_ATTEMPTS) {
                otpStore.remove(email)
                OtpResult.TooManyAttempts
            } else {
                OtpResult.Invalid(data.attempts)
            }
        }

        otpStore.remove(email)
        return OtpResult.Success
    }
}
sealed class OtpResult {
    object Success : OtpResult()
    object Expired : OtpResult()
    object TooManyAttempts : OtpResult()
    data class Invalid(val attempt: Int) : OtpResult()
}
