package com.muhammedihsaan.quizzapp.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muhammedihsaan.quizzapp.data.model.Question
import com.muhammedihsaan.quizzapp.ui.theme.CorrectGreen
import com.muhammedihsaan.quizzapp.ui.theme.ErrorRed
import com.muhammedihsaan.quizzapp.ui.theme.StreakGold
import com.muhammedihsaan.quizzapp.ui.theme.QuizzAppTheme
import com.muhammedihsaan.quizzapp.ui.state.QuizUiState

@Composable
fun QuestionScreen(
    state: QuizUiState,
    onOptionSelected: (Int) -> Unit,
    onSkip: () -> Unit
) {
    QuizzAppTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // High Visibility Progress Bar at the very top of the screen
            LinearProgressIndicator(
                progress = { (state.currentQuestionIndex + 1).toFloat() / state.questions.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
            )

            AnimatedContent(
                targetState = state.currentQuestionIndex,
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInHorizontally { it } + fadeIn() togetherWith
                                slideOutHorizontally { -it } + fadeOut()
                    } else {
                        slideInHorizontally { -it } + fadeIn() togetherWith
                                slideOutHorizontally { it } + fadeOut()
                    }.using(SizeTransform(clip = false))
                },
                modifier = Modifier.weight(1f),
                label = "questionTransition"
            ) { index ->
                val currentQuestion = state.questions.getOrNull(index)
                if (currentQuestion != null) {
                    QuestionContent(
                        question = currentQuestion,
                        state = state,
                        onOptionSelected = onOptionSelected,
                        onSkip = onSkip
                    )
                }
            }
        }
    }
}

@Composable
fun QuestionContent(
    question: Question,
    state: QuizUiState,
    onOptionSelected: (Int) -> Unit,
    onSkip: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Scrollable content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Compact Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Question ${state.currentQuestionIndex + 1} of ${state.questions.size}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                
                AnimatedVisibility(
                    visible = state.currentStreak >= 3,
                    enter = scaleIn() + fadeIn(),
                    exit = scaleOut() + fadeOut()
                ) {
                    Surface(
                        color = StreakGold.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, StreakGold)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "🔥", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${state.currentStreak}",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = StreakGold
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Question Card - Compact
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = question.question,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(20.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 28.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Options - Compact and Clear Feedback
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                question.options.forEachIndexed { index, option ->
                    OptionItem(
                        text = option,
                        isSelected = state.selectedOptionIndex == index,
                        isCorrect = question.correctOptionIndex == index,
                        showResult = state.isAnswered,
                        onClick = { onOptionSelected(index) }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background,
            tonalElevation = 2.dp
        ) {
            OutlinedButton(
                onClick = onSkip,
                enabled = !state.isAnswered,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                )
            ) {
                Text("Skip", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
fun OptionItem(
    text: String,
    isSelected: Boolean,
    isCorrect: Boolean,
    showResult: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = when {
            showResult && isCorrect -> CorrectGreen
            showResult && isSelected && !isCorrect -> ErrorRed
            isSelected -> MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
            else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        },
        label = "color"
    )

    val borderColor by animateColorAsState(
        targetValue = when {
            showResult && isCorrect -> CorrectGreen
            showResult && isSelected && !isCorrect -> ErrorRed
            isSelected -> MaterialTheme.colorScheme.primary
            else -> Color.White.copy(alpha = 0.1f)
        },
        label = "border"
    )
    
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.03f else 1f,
        label = "scale"
    )

    Surface(
        onClick = onClick,
        enabled = !showResult,
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor,
        border = androidx.compose.foundation.BorderStroke(
            width = if (isSelected || (showResult && isCorrect)) 2.dp else 1.dp,
            color = borderColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale),
        shadowElevation = if (isSelected) 6.dp else 0.dp
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = if (showResult && (isCorrect || isSelected)) Color.White else MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = if (isSelected || (showResult && isCorrect)) FontWeight.Bold else FontWeight.Medium
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}
