package com.example.todoapp.ui.presentation.login

import androidx.lifecycle.viewModelScope
import com.example.todoapp.base.BaseViewModel
import com.example.todoapp.data.local.datastore.PreferenceManager
import com.example.todoapp.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val preferenceManager: PreferenceManager
) : BaseViewModel<LoginState, LoginIntent, LoginEffect>(
    LoginState()
) {

    override fun onIntent(intent: LoginIntent) {
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

        updateState { it.copy(isLoading = true, error = null) }
        
        viewModelScope.launch {
            val result = authRepository.login(currentState.email, currentState.password) 
            if (result.isSuccess) {
                preferenceManager.saveLogin(true)
                sendEffect(LoginEffect.NavigateToHome)
            } else {
                updateState { it.copy(isLoading = false, error = result.exceptionOrNull()?.message ?: "Login failed") }
            }
        }
    }
}
