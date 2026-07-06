package com.example.todoapp.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PreferenceManager(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    suspend fun saveOnboardingCompleted(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETED] = value
        }
    }

    suspend fun saveLogin(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = value
        }
    }

    fun onboardingCompleted(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[ONBOARDING_COMPLETED] ?: false
            }
    }

    fun isLoggedIn(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[IS_LOGGED_IN] ?: false
            }
    }
}
