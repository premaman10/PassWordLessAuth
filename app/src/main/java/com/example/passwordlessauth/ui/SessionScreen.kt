package com.example.passwordlessauth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.passwordlessauth.viewmodel.AuthViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
@Composable
fun SessionScreen(
    vm: AuthViewModel
) {
    val state by vm.state.collectAsState()
    val sessionStart = state.sessionStartTime ?: return

    var elapsedTime by remember { mutableStateOf(0L) }

    // Timer side-effect
    LaunchedEffect(sessionStart) {
        while (true) {
            elapsedTime = (System.currentTimeMillis() - sessionStart) / 1000
            delay(1000)
        }
    }

    val minutes = elapsedTime / 60
    val seconds = elapsedTime % 60

    val formatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val startTimeFormatted = formatter.format(Date(sessionStart))

    Column {

        Text(
            text = "Session Started At",
            style = MaterialTheme.typography.titleMedium
        )

        Text(text = startTimeFormatted)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Session Duration",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = String.format("%02d:%02d", minutes, seconds),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { vm.logout() }
        ) {
            Text("Logout")
        }

    }
}
