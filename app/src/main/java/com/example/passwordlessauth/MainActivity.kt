package com.example.passwordlessauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.passwordlessauth.ui.*
import com.example.passwordlessauth.viewmodel.AuthViewModel
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel()
            val state by authViewModel.state.collectAsState()

            NavHost(
                navController = navController,
                startDestination = NavRoutes.LOGIN
            ) {

                composable(NavRoutes.LOGIN) {
                    LoginScreen(authViewModel)
                }

                composable(NavRoutes.OTP) {
                    OtpScreen(authViewModel)
                }

                composable(NavRoutes.SESSION) {
                    SessionScreen(authViewModel)
                }
            }

            // 🔥 Navigation side-effects based on state
            LaunchedEffect(state.isOtpSent, state.isLoggedIn) {
                when {
                    state.isLoggedIn -> {
                        navController.navigate(NavRoutes.SESSION) {
                            popUpTo(0)
                        }
                    }
                    state.isOtpSent -> {
                        navController.navigate(NavRoutes.OTP)
                    }
                }
            }
        }
    }
}
