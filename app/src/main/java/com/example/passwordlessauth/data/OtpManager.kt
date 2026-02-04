package com.example.passwordlessauth.data


class OtpManager {

    private val otpStore = mutableMapOf<String, OtpData>()
    private val OTP_VALIDITY = 60_000L
    private val MAX_ATTEMPTS = 3

    fun generateOtp(email: String): String {
        val otp = (100000..999999).random().toString()
        otpStore[email] = OtpData(
            otp = otp,
            expiryTime = System.currentTimeMillis() + OTP_VALIDITY,
            attempts_Left = MAX_ATTEMPTS
        )
        return otp
    }

    fun validateOtp(email: String, inputOtp: String): Boolean {
        val data = otpStore[email] ?: return false

        if (System.currentTimeMillis() > data.expiryTime) return false
        if (data.attempts_Left <= 0) return false

        if (data.otp == inputOtp) {
            otpStore.remove(email)
            return true
        } else {
            data.attempts_Left--
            return false
        }
    }

    fun attemptsLeft(email: String): Int =
        otpStore[email]?.attempts_Left ?: 0
}
