package com.example.todoapp.data.repository

import com.example.todoapp.data.local.datastore.PreferenceManager
import com.example.todoapp.domain.UserPreferenceRepository
import kotlinx.coroutines.flow.first

class UserPreferenceRepositoryImpl(
    private val preferenceManager: PreferenceManager
)
    : UserPreferenceRepository {

    override suspend fun isOnboardingCompleted(): Boolean {

        return preferenceManager
            .onboardingCompleted()
            .first()
    }

    override suspend fun isLoggedIn(): Boolean {

        return preferenceManager
            .isLoggedIn()
            .first()
    }
}