
package com.example.passwordlessauth.viewmodel

import androidx.lifecycle.ViewModel
import com.example.passwordlessauth.data.OtpManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.passwordlessauth.analytics.AnalyticsLogger
import com.example.passwordlessauth.viewmodel.AuthState

class AuthViewModel : ViewModel() {

    private val otpManager = OtpManager()
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun sendOtp() {
        val email = _state.value.email
        val otp = otpManager.generateOtp(email)
        AnalyticsLogger.logOtpGenerated()
        println("DEBUG OTP: $otp") // allowed (local only)

        _state.value = _state.value.copy(
            isOtpSent = true,
            error = null
        )
    }

    fun verifyOtp(otp: String) {
        val email = _state.value.email
        val success = otpManager.validateOtp(email, otp)

        if (success) {
            AnalyticsLogger.logOtpSuccess()
            _state.value = _state.value.copy(
                isLoggedIn = true,
                sessionStartTime = System.currentTimeMillis()
            )
        } else {
            AnalyticsLogger.logOtpFailure()
            _state.value = _state.value.copy(
                error = "Invalid or expired OTP"
            )
        }
    }

    fun logout() {
        AnalyticsLogger.logLogout()
        _state.value = AuthState()
    }
}
