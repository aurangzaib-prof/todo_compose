package com.example.todoapp.ui.presentation.signup

import androidx.lifecycle.viewModelScope
import com.example.todoapp.base.BaseViewModel
import com.example.todoapp.data.local.datastore.PreferenceManager
import com.example.todoapp.data.local.room.User
import com.example.todoapp.data.repository.AuthRepository
import kotlinx.coroutines.launch

class SignupViewModel(
    private val authRepository: AuthRepository,
    private val preferenceManager: PreferenceManager
) : BaseViewModel<SignupState, SignupIntent, SignupEffect>(
    SignupState()
) {

    override fun onIntent(intent: SignupIntent) {
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

        updateState { it.copy(isLoading = true, error = null) }

        val user = User(
            email = currentState.email,
            name = currentState.name,
            password = currentState.password
        )

        viewModelScope.launch {
            val result = authRepository.register(user)
            if (result.isSuccess) {
                preferenceManager.saveLogin(true)
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
