package com.example.todoapp.ui.presentation.signup

import androidx.lifecycle.viewModelScope
import com.example.todoapp.base.BaseViewModel
import com.example.todoapp.data.local.datastore.PreferenceManager
import com.example.todoapp.data.local.room.auth_database.AuthEntity
import com.example.todoapp.data.repository.MainRepository
import com.example.todoapp.ui.extensions.isValidEmail
import kotlinx.coroutines.launch

class SignupViewModel(
    private val authRepository: MainRepository,
    private val preferenceManager: PreferenceManager
) : BaseViewModel<SignupState, SignupIntent, SignupEffect>(
    SignupState()
) {

    override suspend fun onIntent(intent: SignupIntent) {
        when (intent) {
            is SignupIntent.NameChanged -> updateState { it.copy(name = intent.name) }
            is SignupIntent.EmailChanged -> updateState { it.copy(email = intent.email) }
            is SignupIntent.PasswordChanged -> updateState { it.copy(password = intent.password) }
            SignupIntent.SignupClicked -> handleSignup()
            SignupIntent.SigninClicked -> {
                sendEffect(SignupEffect.NavigateToLogin)
            }
        }
    }

    private fun handleSignup() {
        if (currentState.name.isBlank() || currentState.email.isBlank() || currentState.password.isBlank()) {
            updateState { it.copy(error = "Please fill all fields") }
            return
        }

        if (!currentState.email.isValidEmail()) {
            updateState { it.copy(error = "Please enter a valid email") }
            return
        }

        updateState { it.copy(isLoading = true, error = null) }

        val authEntity = AuthEntity(
            email = currentState.email,
            name = currentState.name,
            password = currentState.password
        )

        viewModelScope.launch {
            val result = authRepository.register(authEntity)
            if (result.isSuccess) {
                preferenceManager.saveLogin(true, currentState.email)
                sendEffect(SignupEffect.NavigateToHome)
            } else {
                updateState {
                    it.copy(
                        isLoading = false,
                        error = result.exceptionOrNull()?.message ?: "Signup failed"
                    )
                }
            }
        }
    }
}
