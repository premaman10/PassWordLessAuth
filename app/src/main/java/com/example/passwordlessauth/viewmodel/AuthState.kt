package com.example.passwordlessauth.viewmodel

data class AuthState(
    val email: String = "",
    val isOtpSent: Boolean = false,
    val isLoggedIn: Boolean = false,
    val error: String? = null,
    val sessionStartTime: Long? = null
)
