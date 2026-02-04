package com.example.passwordlessauth.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpScreen(
    email: String,
    remainingSeconds: Int,
    errorMessage: String?,
    onVerify: (String) -> Unit,
    onResend: () -> Unit
) {
    var otp by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(0.9f),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "Verify OTP",
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "Sent to $email",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Expires in $remainingSeconds seconds",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp
                )

                // 🔴 Error message (edge cases)
                if (errorMessage != null) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                OutlinedTextField(
                    value = otp,
                    onValueChange = { otp = it },
                    label = { Text("OTP") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = { onVerify(otp) },
                    enabled = remainingSeconds > 0,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Verify OTP")
                }

                TextButton(
                    onClick = onResend,
                    enabled = remainingSeconds == 0,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Resend OTP")
                }
            }
        }
    }
}
