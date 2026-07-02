package com.example.todoapp.ui.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.base.MviViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(), MviViewModel<LoginState, LoginIntent, LoginEffect> {

    private val _uiState = MutableStateFlow(LoginState())
    override val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<LoginEffect>()
    val effect: SharedFlow<LoginEffect> = _effect.asSharedFlow()

    override fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> {
                _uiState.update { it.copy(email = intent.email) }
            }
            LoginIntent.LoginClicked -> handleLogin()
            LoginIntent.SignupClicked -> {
                viewModelScope.launch { _effect.emit(LoginEffect.NavigateToSignup) }
            }
        }
    }

    private fun handleLogin() {
        viewModelScope.launch {
            _effect.emit(LoginEffect.NavigateToHome)
        }
    }
}
