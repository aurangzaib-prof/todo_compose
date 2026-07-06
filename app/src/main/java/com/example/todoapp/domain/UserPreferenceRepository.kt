package com.example.todoapp.domain

interface UserPreferenceRepository {
    suspend fun isOnboardingCompleted(): Boolean
    suspend fun isLoggedIn(): Boolean
}