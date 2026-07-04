# Quizz App

A modern, high-performance Android Quiz Application built with Jetpack Compose and following the **MVVM (Model-View-ViewModel)** architecture.

## 🚀 Features

### 1. Dynamic Content & Flow
- **API Integration**: Fetches real-time quiz questions from a remote JSON gist using **Retrofit 3**.
- **Instant Feedback**: 
  - On tapping an option, the correct answer turns **Solid Green**.
  - If the user selects the wrong option, it turns **Solid Red**.
  - A **2-second delay** is implemented after answering to allow the user to review the feedback before auto-advancing.
- **Sticky Skip Action**: The "Skip" button is fixed at the bottom of the screen for easy access across all device sizes.
- **Streak Logic**: Tracks consecutive correct answers. A "Fire" badge (🔥) appears when the user achieves a streak of 3 or more.

### 2. Premium UI/UX
- **Dual-Theme Experience**: 
  - **Light Theme Loading**: A clean, bright splash/loading screen.
  - **Excellent Dark Theme Quiz**: An immersive, high-contrast dark theme for the main quiz and results screens.
- **Smooth Animations**: Uses `AnimatedContent` for horizontal slide transitions between questions and `AnimatedVisibility` for the streak badge.
- **Scrollable Content**: Handles long question text and multiple options gracefully with a dedicated scrollable area.
- **Progress Tracking**: A persistent, full-width `LinearProgressIndicator` at the very top of the screen.

### 3. Results Summary
- Displays a final performance breakdown: **Correct/Total Questions**.
- Highlights the **Max Streak** achieved during the session.
- Tracks and displays the number of **Skipped Questions**.
- One-tap "Restart Quiz" functionality.

## 🛠️ Implementation Details
- **Architecture**: MVVM with a clean separation of Data, Logic, and UI.
- **UI State**: Managed via a dedicated `QuizUiState` data class and `StateFlow` in the ViewModel.
- **Networking**: Retrofit 3.0.0 with Kotlinx Serialization Converter.
- **Animations**: Jetpack Compose Animation API (`animateColorAsState`, `animateFloatAsState`).
- **Target SDK**: Android 15 (API 35) / Compiled against SDK 35 for latest platform features.

## 📖 How to Run
1. Open the project in **Android Studio** (Ladybug or later recommended).
2. Sync Gradle to download dependencies.
3. Run on a physical device or emulator with **Internet access**.

---
*Developed with focus on Clean Code, User Experience, and Architectural Best Practices.*
