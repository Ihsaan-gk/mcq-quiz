# Quizz App

A modern, high-performance Android Quiz Application built with Jetpack Compose and following the **MVVM (Model-View-ViewModel)** architecture.

---

# 🚀 Features

## 1. Dynamic Content & Quiz Flow

### API Integration
- Fetches real-time quiz questions from a remote JSON API using **Retrofit 3**.
- Parses responses using **Kotlinx Serialization**.

### Instant Answer Feedback
- Correct answer is highlighted in **Solid Green**.
- Incorrect selected answer is highlighted in **Solid Red**.
- Users can clearly review their answer before moving forward.

### Auto Navigation
- After selecting an answer, the app waits for **2 seconds** and automatically navigates to the next question.

### Sticky Skip Action
- The **Skip** button remains fixed at the bottom of the screen for quick and convenient access.

### Streak Logic
- Tracks consecutive correct answers.
- Displays a **🔥 Fire Badge** when the user achieves a streak of 3 or more correct answers.

### Question Navigation
- Supports smooth navigation between questions.
- Swipe gestures can be used for a more natural quiz-taking experience.

---

# 🎨 Premium UI/UX

## Dual Theme Experience

### Light Theme Loading Screen
- Clean and minimal loading experience.
- Smooth transition into the quiz.

### Dark Theme Quiz Experience
- High-contrast modern dark theme.
- Better readability and immersive user experience.

## Animations

### Question Transition
- Smooth horizontal slide animations between questions using `AnimatedContent`.

### Streak Animation
- Fire streak badge uses `AnimatedVisibility` for engaging feedback.

### Interactive Feedback
- Option colors animate smoothly using `animateColorAsState`.

## Scrollable Content
- Long questions and multiple options are handled gracefully.
- Fully scrollable content area ensures compatibility with all screen sizes.

## Progress Tracking
- Full-width `LinearProgressIndicator`.
- Displays quiz completion progress at the top of the screen.

---

# 📊 Results Summary

After completing the quiz, users can view:

- Total Questions
- Correct Answers
- Final Score
- Maximum Streak Achieved
- Total Skipped Questions

### Restart Quiz
- One-tap restart functionality to retake the quiz instantly.

---

# 🏗️ Architecture

The project follows a clean **MVVM Architecture**.

## Layers

### Data Layer
Responsible for:
- API communication
- Repository implementation
- Data models

### Domain / Logic Layer
Responsible for:
- Business logic
- Streak calculations
- Quiz state management

### UI Layer
Responsible for:
- Compose screens
- State rendering
- User interactions

---

# 🛠️ Tech Stack

### Language
- Kotlin

### UI
- Jetpack Compose
- Material 3

### Architecture
- MVVM
- StateFlow

### Networking
- Retrofit 3.0.0
- Kotlinx Serialization

### Asynchronous Programming
- Kotlin Coroutines

### Animations
- AnimatedContent
- AnimatedVisibility
- animateColorAsState
- animateFloatAsState

### Build Configuration
- Compile SDK: 35
- Target SDK: 35
- Minimum SDK: 24

---

# 📂 Project Structure

```text
com.example.quizzapp
│
├── data
│   ├── api
│   ├── model
│   └── repository
│
├── ui
│   ├── screens
│   ├── components
│   └── theme
│
├── viewmodel
│
└── MainActivity
```

---

# 📥 Clone & Run the Project

## Clone Repository

```bash
git clone https://github.com/Ihsaan-gk/mcq-quiz.git
```

Navigate into the project:

```bash
cd mcq-quiz
```

---

# ▶️ Run the Application

### Prerequisites

- Android Studio Ladybug (or newer)
- JDK 17+
- Android SDK 35
- Internet Connection

### Steps

1. Open Android Studio.
2. Click **Open Existing Project**.
3. Select the cloned repository folder.
4. Allow Gradle Sync to complete.
5. Connect an Android device or start an emulator.
6. Click **Run ▶**.

---

# 📦 Download Without Git

If you prefer not to use Git:

1. Open the repository:
   https://github.com/Ihsaan-gk/mcq-quiz

2. Click:

   **Code → Download ZIP**

3. Extract the ZIP file.

4. Open the extracted folder in Android Studio.

5. Sync Gradle and run the application.

---

# 🔗 GitHub Repository

Repository URL:

https://github.com/Ihsaan-gk/mcq-quiz

---

# ✨ Highlights

✔ MVVM Architecture

✔ StateFlow State Management

✔ Retrofit API Integration

✔ Dynamic Quiz Experience

✔ Skip Functionality

✔ Streak Tracking

✔ Smooth Animations

✔ Material 3 UI

✔ Dark Theme Experience

✔ Responsive Layouts

✔ Accessibility-Friendly Design

✔ Clean Code Structure

---

# 📖 Implementation Summary

The application was developed with a strong focus on:

- Clean Architecture
- Separation of Concerns
- Maintainability
- Scalability
- User Experience
- Performance

The implementation demonstrates complete end-to-end quiz functionality including API handling, question navigation, answer validation, streak tracking, result generation, animations, and responsive UI behavior.

---

**Developed by Muhammed Ihsaan**

Android Developer | Kotlin | Jetpack Compose | MVVM
