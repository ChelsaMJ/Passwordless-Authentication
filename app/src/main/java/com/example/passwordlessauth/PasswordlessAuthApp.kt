package com.example.passwordlessauth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.passwordlessauth.ui.LoginScreen
import com.example.passwordlessauth.ui.OtpScreen
import com.example.passwordlessauth.ui.SessionScreen
import com.example.passwordlessauth.viewmodel.AuthState
import com.example.passwordlessauth.viewmodel.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PasswordlessAuthApp() {

    val viewModel: AuthViewModel = viewModel()

    when (val state = viewModel.authState) {

        is AuthState.Login -> {
            LoginScreen {
                viewModel.sendOtp(it)
            }
        }

        is AuthState.Otp -> {
            OtpScreen(
                email = state.email,
                remainingSeconds = viewModel.remainingSeconds,
                errorMessage = viewModel.otpErrorMessage,
                onVerify = {
                    viewModel.verifyOtp(state.email, it)
                },
                onResend = {
                    viewModel.sendOtp(state.email)
                }
            )
        }


        is AuthState.Session -> {
            SessionScreen(state.startTime) {
                viewModel.logout()
            }
        }
    }
}
