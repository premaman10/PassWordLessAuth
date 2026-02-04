package com.example.passwordlessauth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.passwordlessauth.viewmodel.AuthViewModel

@Composable
fun LoginScreen(vm: AuthViewModel = viewModel()) {
    val state by vm.state.collectAsState()

    Column {
        TextField(
            value = state.email,
            onValueChange = { vm.onEmailChange(it) },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            isError = state.error != null
        )

        Button(onClick = vm::sendOtp) {
            Text("Send OTP")
        }

        state.error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
