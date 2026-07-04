package com.muhammedihsaan.quizzapp.ui.state

import com.muhammedihsaan.quizzapp.data.model.Question

data class QuizUiState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val isLoading: Boolean = true,
    val score: Int = 0,
    val currentStreak: Int = 0,
    val maxStreak: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isAnswered: Boolean = false,
    val isQuizFinished: Boolean = false,
    val skippedQuestions: Int = 0
)
