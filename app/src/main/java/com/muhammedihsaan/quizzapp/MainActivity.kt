package com.muhammedihsaan.quizzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muhammedihsaan.quizzapp.ui.screens.LoadingScreen
import com.muhammedihsaan.quizzapp.ui.screens.QuestionScreen
import com.muhammedihsaan.quizzapp.ui.screens.ResultScreen
import com.muhammedihsaan.quizzapp.ui.theme.QuizzAppTheme
import com.muhammedihsaan.quizzapp.ui.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizzAppTheme {
                val viewModel: QuizViewModel = viewModel()
                val uiState by viewModel.uiState.collectAsState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val modifier = Modifier.padding(innerPadding)
                    
                    androidx.compose.foundation.layout.Box(modifier = modifier) {
                        when {
                            uiState.isLoading -> LoadingScreen()
                            uiState.isQuizFinished -> ResultScreen(
                                state = uiState,
                                onRestart = viewModel::restartQuiz
                            )
                            else -> QuestionScreen(
                                state = uiState,
                                onOptionSelected = viewModel::selectOption,
                                onSkip = viewModel::skipQuestion
                            )
                        }
                    }
                }
            }
        }
    }
}
