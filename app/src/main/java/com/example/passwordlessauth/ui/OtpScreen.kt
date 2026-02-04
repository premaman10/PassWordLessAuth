package com.example.passwordlessauth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.passwordlessauth.viewmodel.AuthViewModel

@Composable
fun OtpScreen(
    vm: AuthViewModel
) {
    val state by vm.state.collectAsState()
    var otpInput by rememberSaveable { mutableStateOf("") }

    Column {

        Text(
            text = "Enter OTP sent to ${state.email}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = otpInput,
            onValueChange = {
                if (it.length <= 6) otpInput = it
            },
            label = { Text("6-digit OTP") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                vm.verifyOtp(otpInput)
            },
            enabled = otpInput.length == 6
        ) {
            Text("Verify OTP")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                otpInput = ""
                vm.sendOtp()
            }
        ) {
            Text("Resend OTP")
        }

        Spacer(modifier = Modifier.height(12.dp))

        state.error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
