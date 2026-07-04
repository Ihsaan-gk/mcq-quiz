package com.muhammedihsaan.quizzapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammedihsaan.quizzapp.data.repository.QuizRepository
import com.muhammedihsaan.quizzapp.ui.state.QuizUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizViewModel(private val repository: QuizRepository = QuizRepository()) : ViewModel() {

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        viewModelScope.launch {
            try {
                val questions = repository.getQuestions()
                _uiState.update { it.copy(questions = questions, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun selectOption(index: Int) {
        if (_uiState.value.isAnswered) return

        val currentQuestion = _uiState.value.questions[_uiState.value.currentQuestionIndex]
        val isCorrect = index == currentQuestion.correctOptionIndex

        _uiState.update { state ->
            val newCurrentStreak = if (isCorrect) state.currentStreak + 1 else 0
            state.copy(
                selectedOptionIndex = index,
                isAnswered = true,
                score = if (isCorrect) state.score + 1 else state.score,
                currentStreak = newCurrentStreak,
                maxStreak = maxOf(state.maxStreak, newCurrentStreak)
            )
        }

        viewModelScope.launch {
            delay(2000)
            moveToNextQuestion()
        }
    }

    fun skipQuestion() {
        if (_uiState.value.isAnswered) return
        
        _uiState.update { it.copy(skippedQuestions = it.skippedQuestions + 1, currentStreak = 0) }
        moveToNextQuestion()
    }

    private fun moveToNextQuestion() {
        val nextIndex = _uiState.value.currentQuestionIndex + 1
        if (nextIndex < _uiState.value.questions.size) {
            _uiState.update {
                it.copy(
                    currentQuestionIndex = nextIndex,
                    selectedOptionIndex = null,
                    isAnswered = false
                )
            }
        } else {
            _uiState.update { it.copy(isQuizFinished = true) }
        }
    }

    fun restartQuiz() {
        _uiState.update {
            it.copy(
                currentQuestionIndex = 0,
                score = 0,
                currentStreak = 0,
                maxStreak = 0,
                selectedOptionIndex = null,
                isAnswered = false,
                isQuizFinished = false,
                skippedQuestions = 0
            )
        }
    }
}
