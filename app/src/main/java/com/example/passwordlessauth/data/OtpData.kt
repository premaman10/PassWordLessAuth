package com.example.passwordlessauth.data

data class OtpData(
    //datafield
    val otp: String,
    val createdAt: Long,
    var attempts: Int = 0
)
