package com.example.passwordlessauth.data

data class OtpData(
    val otp: String,
    val createdAt: Long,
    var attempts: Int = 0
)
