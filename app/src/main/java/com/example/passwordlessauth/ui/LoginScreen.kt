package com.example.passwordlessauth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.passwordlessauth.viewmodel.AuthViewModel


@Composable
fun LoginScreen(vm: AuthViewModel = viewModel()) {
    val state by vm.state.collectAsState()

    Column {
        TextField(
            value = state.email,
            onValueChange = vm::onEmailChange,
            label = { Text("Email") }
        )

        Button(onClick = vm::sendOtp) {
            Text("Send OTP")
        }

        state.error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}
