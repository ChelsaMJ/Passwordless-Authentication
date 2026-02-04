package com.example.passwordlessauth.analytics

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

class AnalyticsLogger(context: Context) {

    private val analytics: FirebaseAnalytics = Firebase.analytics

    fun logOtpGenerated(email: String) {
        analytics.logEvent("otp_generated") {
            param("email", email)
        }
    }

    fun logOtpSuccess(email: String) {
        analytics.logEvent("otp_success") {
            param("email", email)
        }
    }

    fun logOtpFailure(email: String) {
        analytics.logEvent("otp_failure") {
            param("email", email)
        }
    }

    fun logLogout() {
        analytics.logEvent("logout") {}
    }
}
