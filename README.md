# Passwordless Authentication App (Email + OTP)

## Overview
This Android application implements a **passwordless authentication flow** using **Email + OTP**, followed by a **session screen** that tracks login duration in real time.  
All OTP logic is implemented **locally** without any backend, as required by the assignment.

The app is built using **Kotlin + Jetpack Compose**, follows **ViewModel-based state management**, and integrates **Firebase Analytics** for event tracking.

---

## Tech Stack
- **Language:** Kotlin  
- **UI:** Jetpack Compose (Material 3)  
- **Architecture:** ViewModel + UI State (One-way data flow)  
- **Concurrency:** Kotlin Coroutines  
- **Analytics SDK:** Firebase Analytics  
- **IDE:** Android Studio  

---

## Authentication Flow

### 1. Email + OTP Login
1. User enters an email address
2. Taps **Send OTP**
3. A **6-digit OTP** is generated locally
4. User enters OTP to verify and log in

⚠️ OTP is **not emailed** (no backend).  
For testing and demo purposes, the OTP is logged in **Logcat**.

---

## OTP Rules & Handling

| Rule | Implementation |
|----|----|
OTP length | 6 digits |
OTP expiry | 60 seconds (countdown) |
Maximum attempts | 3 |
Resend OTP | Allowed after expiry |
Old OTP invalidation | Yes |
Attempts reset on resend | Yes |

---

## OTP Data Storage

OTP data is stored **per email address** using an in-memory data structure:

```
Map<String, OtpData>
```

## OTP Data Storage

**OtpData contains:**
- `otp` → generated OTP
- `timestamp` → OTP generation time
- `attempts` → number of failed attempts

### Why a Map?
- Constant-time lookup by email
- Clean separation of OTP data per user
- Easy invalidation and reset on resend
- No backend or persistence required

This satisfies the requirement:
> **You must store OTP data per email.**

---

## ⚠️ Edge Cases Handled
- Incorrect OTP → error message displayed
- OTP expired → verification disabled
- Maximum attempts exceeded → resend required
- Resend OTP → new OTP generated, countdown reset
- Screen rotation → state preserved

---

## Session Screen
After successful login:
- Session start time is recorded
- Live session duration is shown in **mm:ss**
- Timer survives recomposition and rotation
- Logout ends the session and returns to login screen

---

## State & Rotation Handling
- Business state is stored in the **ViewModel**, which survives configuration changes
- UI state uses `rememberSaveable` where appropriate
- Time-based values are derived instead of stored
- Screen rotation does not reset OTP or session state

---

## Firebase Analytics Integration

### Why Firebase Analytics?
- Lightweight and easy to integrate
- No backend logic required
- Suitable for event tracking only

### Events Logged
- `otp_generated`
- `otp_failure`
- `otp_success`
- `logout`

Events were verified using **Firebase Analytics DebugView** during development.

---

## How to Run the App
1. Open the project in **Android Studio**
2. Let Gradle sync complete
3. Start an **Android Emulator**
4. Click ▶ **Run**
- Ensure `google-services.json` is present in the `app/` directory

No extra setup steps are required.

---

## Demo Video
The demo video demonstrates:
- Email entry and OTP generation
- OTP verification (success and failure)
- Countdown timer behavior
- Session screen with live timer
- Logout flow
- Firebase Analytics DebugView events

---

## AI Usage Disclosure
GPT was used as a **reference tool** for:
- Clarifying Jetpack Compose patterns
- Understanding Firebase Analytics setup
- Verifying architectural decisions

All OTP logic, state handling, UI composition, and edge-case handling were **understood and implemented manually**.

---

## Conclusion
This project demonstrates:
- Correct use of Jetpack Compose
- Clean ViewModel-driven architecture
- Proper OTP logic and edge-case handling
- Rotation-safe state management
- Correct SDK integration without backend dependency
