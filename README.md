# Passwordless Authentication App (Email + OTP)

This project is an Android application built using **Kotlin** and **Jetpack Compose** that demonstrates a **passwordless authentication flow** using **Email + OTP**, followed by a **session screen** that tracks login duration in real time.

The goal of this assignment was not just to make the app work, but to demonstrate **clear understanding of state management, architecture, and modern Android development practices**, without relying on any backend.

---

## Tech Stack

- **Language:** Kotlin  
- **UI:** Jetpack Compose (Material 3)  
- **Architecture:** Single Activity + ViewModel + UI State  
- **State Management:** StateFlow, `remember`, `rememberSaveable`  
- **Async / Side Effects:** Kotlin Coroutines, `LaunchedEffect`  
- **Navigation:** Navigation Compose  
- **External SDK:** Timber (Logging)

---

## App Flow

1. User enters an email address  
2. User requests an OTP  
3. A 6-digit OTP is generated locally  
4. User verifies the OTP  
5. On success, a session screen is shown  
6. Session start time and live duration (mm:ss) are displayed  
7. User can logout and return to the login screen  

All logic is implemented **locally**, without any backend.

---

## OTP Logic & Expiry Handling

- OTP length is **6 digits**
- OTP validity is **60 seconds**
- Maximum **3 attempts** are allowed
- OTP is stored **per email**
- Generating a new OTP:
  - Invalidates the previous OTP
  - Resets the attempt count

### Implementation Details

OTP logic is handled in a dedicated manager class using system time:

- When an OTP is generated, the current time is stored
- Expiry is calculated using:
  `System.currentTimeMillis() + 60 seconds`
- During validation:
  - Expiry time is checked
  - Attempt count is reduced on failure
  - OTP is rejected if expired, incorrect, or attempts are exhausted

This approach avoids timers in the ViewModel and keeps business logic clean, predictable, and testable.

---

## Data Structures Used

A `MutableMap<String, OtpData>` is used to store OTP information.

- **Key:** Email address  
- **Value:** `OtpData` object containing:
  - OTP value
  - Expiry timestamp
  - Remaining attempts

This structure allows:
- Fast lookup per email
- Easy OTP invalidation on resend
- Clean separation of authentication state

Global mutable state was intentionally avoided to maintain clean architecture and predictable behavior.

---

## External SDK Choice: Timber

**Timber** was used as the external SDK for logging important events such as:
- OTP generation
- OTP validation success
- OTP validation failure
- Logout

### Why Timber?

- Lightweight and easy to integrate
- Works completely offline
- Logs are immediately visible in Logcat
- Commonly used in real production apps

### Why not Firebase Analytics or Sentry?

- **Firebase Analytics** requires backend setup, internet connectivity, and a Firebase console, which is unnecessary for a local-logic assignment.
- **Sentry** is mainly used for crash reporting and would be overkill for this use case.

Timber keeps the focus on **application logic and architecture**, which aligns with the intent of the assignment.

---

## Use of GPT vs My Own Understanding

AI tools were allowed for this assignment and were used responsibly.

### Where GPT helped
- Understanding trade-offs (e.g., Navigation Compose vs Intents)
- Clarifying why certain tools (Timber vs Firebase/Sentry) were more appropriate
- Debugging Compose + Kotlin 2.0 compiler configuration
- Verifying best practices for `LaunchedEffect` and state-driven navigation

I frequently asked **“why this and not that”** to understand the reasoning before implementing.

### What I implemented and understood myself
- OTP generation, expiry, and attempt handling logic
- Data structure selection and usage
- ViewModel and UI state separation
- StateFlow-based architecture
- Compose UI screens (Login, OTP, Session)
- Session timer using `LaunchedEffect`
- Logout handling and cleanup
- Single-activity, state-driven navigation

The code was not blindly copied — it was built step-by-step with understanding and iteration.

---

## Edge Cases Handled

- Expired OTP  
- Incorrect OTP  
- Exceeded OTP attempts  
- OTP resend flow  
- Screen rotation without losing state  
- Timer stopping correctly on logout  

---

## How to Run

1. Clone the repository  
2. Open the project in Android Studio (latest version recommended)  
3. Let Gradle sync  
4. Run on an emulator or physical device  

No additional setup is required.

---

## Final Notes

This project demonstrates:
- Modern Android development with Jetpack Compose
- Clean, state-driven architecture
- Thoughtful technical decision-making
- Clear understanding of **why** design choices were made

The focus was on correctness, clarity, and learning — not just completing the task.
