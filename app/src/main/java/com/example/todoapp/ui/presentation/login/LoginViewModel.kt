package com.example.todoapp.ui.presentation.login

import androidx.lifecycle.viewModelScope
import com.example.todoapp.base.BaseViewModel
import com.example.todoapp.data.local.datastore.PreferenceManager
import com.example.todoapp.data.repository.MainRepository
import com.example.todoapp.ui.extensions.isValidEmail
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: MainRepository,
    private val preferenceManager: PreferenceManager
) : BaseViewModel<LoginState, LoginIntent, LoginEffect>(
    LoginState()
) {

    override suspend fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> {
                updateState { it.copy(email = intent.email) }
            }
            is LoginIntent.PasswordChanged -> {
                updateState { it.copy(password = intent.password) }
            }
            LoginIntent.LoginClicked -> handleLogin()
            LoginIntent.SignupClicked -> {
                sendEffect(LoginEffect.NavigateToSignup)
            }
        }
    }

    private fun handleLogin() {
        if (currentState.email.isBlank() || currentState.password.isBlank()) {
            updateState { it.copy(error = "Please fill all fields") }
            return
        }

        if (!currentState.email.isValidEmail()) {
            updateState { it.copy(error = "Please enter a valid email") }
            return
        }

        updateState { it.copy(isLoading = true, error = null) }
        
        viewModelScope.launch {
            val result = authRepository.login(currentState.email, currentState.password) 
            if (result.isSuccess) {
                preferenceManager.saveLogin(true, currentState.email)
                sendEffect(LoginEffect.NavigateToHome)
            } else {
                updateState { it.copy(isLoading = false, error = result.exceptionOrNull()?.message ?: "Login failed") }
            }
        }
    }
}
