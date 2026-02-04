
package com.example.passwordlessauth.viewmodel

import androidx.lifecycle.ViewModel
import com.example.passwordlessauth.data.OtpManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.passwordlessauth.analytics.AnalyticsLogger
import com.example.passwordlessauth.data.OtpResult
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

        if (!isValidEmail(email)) {
            _state.value = _state.value.copy(
                error = "Please enter a valid email address"
            )
            return
        }

        otpManager.generateOtp(email)
        AnalyticsLogger.logOtpGenerated()

        _state.value = _state.value.copy(
            isOtpSent = true,
            error = null
        )
    }

    fun verifyOtp(otp: String) {
        when (val result = otpManager.validateOtp(_state.value.email, otp)) {

            is OtpResult.Success -> {
                AnalyticsLogger.logOtpSuccess()
                _state.value = _state.value.copy(
                    isLoggedIn = true,
                    sessionStartTime = System.currentTimeMillis()
                )
            }

            is OtpResult.Expired -> {
                _state.value = _state.value.copy(
                    error = "OTP expired. Please request a new OTP."
                )
            }

            is OtpResult.Invalid -> {
                _state.value = _state.value.copy(
                    error = "Invalid OTP (Attempt ${result.attempt} of 3)"
                )
            }

            is OtpResult.TooManyAttempts -> {
                _state.value = AuthState(
                    error = "Too many incorrect attempts. Please enter email again."
                )
            }
        }
    }

    fun logout() {
        AnalyticsLogger.logLogout()
        _state.value = AuthState()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
