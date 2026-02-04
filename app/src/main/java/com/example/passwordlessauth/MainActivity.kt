package com.example.passwordlessauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.passwordlessauth.ui.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.passwordlessauth.ui.theme.PasswordlessAuthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        setContent {
            PasswordlessAuthTheme {
                val navController = rememberNavController()
                val authViewModel: AuthViewModel = viewModel()
                val state by authViewModel.state.collectAsState()

            // 🔥 State-driven navigation
            LaunchedEffect(state.isOtpSent, state.isLoggedIn) {
                when {
                    state.isLoggedIn -> {
                        navController.navigate(NavRoutes.SESSION) {
                            popUpTo(NavRoutes.LOGIN) { inclusive = true }
                        }
                    }

                    state.isOtpSent -> {
                        navController.navigate(NavRoutes.OTP)
                    }

                    else -> {
                        navController.navigate(NavRoutes.LOGIN) {
                            popUpTo(0)
                        }
                    }
                }
            }

            // 🎨 UI only here
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
            }
        }
    }
}
