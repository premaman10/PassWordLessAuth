<div align="center">
  <img src="https://github.com/premaman10/PassWordLessAuth/blob/master/app/src/main/res/drawable/app_logo.png" alt="PasswordlessAuth Logo" width="200"/>
  <h1>PasswordlessAuth</h1>
  <p><strong>A Secure, Seamless, and Premium Authentication Experience</strong></p>

  [![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple.svg?style=flat&logo=kotlin)](https://kotlinlang.org/)
  [![Android](https://img.shields.io/badge/Android-Jetpack_Compose-green.svg?style=flat&logo=android)](https://developer.android.com/jetpack/compose)
  [![Material 3](https://img.shields.io/badge/UI-Material_3-blue.svg?style=flat&logo=material-design)](https://m3.material.io/)
  [![Lokal App Assignment](https://img.shields.io/badge/Assignment-Lokal_App-orange.svg?style=flat)](https://www.lokal.app/)
</div>

---

## 👨‍💻 Developed by Prem Aman
This project was developed as a technical assignment for the **Lokal App** team. The goal was to build a robust, production-ready passwordless authentication flow that prioritizes user experience and security without needing a backend.

---

## ✨ Features

- 📧 **Passwordless Login:** Fast and secure email-based authentication using OTP (One-Time Password).
- 💎 **Glassmorphism UI:** A modern, premium design system featuring translucent surfaces and vibrant gradients.
- 🔄 **State-Driven Navigation:** Smooth transitions between Login, OTP, and Session screens using Navigation Compose.
- ⏱️ **Real-time Session Tracking:** Live session duration display with accurate management.
- 🛡️ **Advanced OTP Logic:** 
  - 60-second expiration window.
  - Multi-attempt protection (Max 3 attempts).
  - Secure local invalidation on resend.
- 📈 **Integrated Analytics:** Event logging using the Timber SDK for operational visibility.

---

## 🛠 Tech Stack & Architecture

- **Language:** 100% Kotlin
- **UI Framework:** Jetpack Compose (Material 3)
- **Architecture:** Single Activity + ViewModel + MVI-style UI State
- **Reactive Programming:** StateFlow & Kotlin Coroutines
- **Logging/Analytics:** Timber SDK
- **Design:** AI-Assisted Frontend & Aesthetic Optimization

> [!NOTE]
> **AI Assistance Disclosure:** To ensure industry-leading aesthetics and development efficiency, the **UI design and front-end polishing** were optimized with the help of advanced AI tools. This allowed for a higher level of visual excellence and cleaner styling tokens while keeping the core business logic hand-crafted.

---

## 📸 visual Walkthrough

### 1. Secure Entry
The entry point focuses on simplicity. The user provides an email and triggers the secure OTP generation.

### 2. Time-Sensitive Verification
A clean OTP input field with real-time feedback. The logic handles expiration and incorrect attempts gracefully to prevent brute-force attacks.

### 3. Active Session
Once verified, the user enters a premium session screen showing active login time, reinforcing the sense of a secure, live authentication state.

---

## 🧩 Technical Deep Dive: OTP Logic

The app uses a custom `OtpManager` that handles the core security logic:

```kotlin
// Secure state management for OTPs
private val otpStore = mutableMapOf<String, OtpData>()
private val OTP_VALIDITY = 60_000L // 60s
private val MAX_ATTEMPTS = 3
```

- **Persistence:** OTPs are stored in-memory during the session.
- **Expiry:** Calculated using `System.currentTimeMillis()`.
- **Cleanup:** State is cleared immediately upon successful verification or exhaustion of attempts.

---

## 🚀 Getting Started

1.  **Clone the Repo:** `git clone https://github.com/[your-username]/PasswordlessAuth.git`
2.  **Open in Android Studio:** Use the latest version of Arctic Fox or later.
3.  **Sync & Build:** Let Gradle download dependencies.
4.  **Run:** Deployment to any emulator or physical device running API 24+.

---

<div align="center">
  <p>Built with ❤️ by <strong>Prem Aman</strong> for <strong>Lokal App</strong></p>
</div>
