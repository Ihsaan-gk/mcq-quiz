package com.muhammedihsaan.quizzapp.data.repository

import com.muhammedihsaan.quizzapp.data.api.QuizApiService
import com.muhammedihsaan.quizzapp.data.model.Question
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class QuizRepository {
    private val json = Json { ignoreUnknownKeys = true }
    private val contentType = "application/json".toMediaType()

    private val apiService = Retrofit.Builder()
        .baseUrl(QuizApiService.BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
        .create(QuizApiService::class.java)

    suspend fun getQuestions(): List<Question> {
        return apiService.getQuestions()
    }
}
