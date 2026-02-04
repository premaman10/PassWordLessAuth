package com.example.passwordlessauth.data

data class OtpData(
    val otp: String,
    val expiryTime: Long,
    var attempts_Left: Int
)
