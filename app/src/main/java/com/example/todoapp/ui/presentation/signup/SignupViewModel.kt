package com.example.todoapp.ui.presentation.signup

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

class SignupViewModel : ViewModel(), MviViewModel<SignupState, SignupIntent, SignupEffect> {

    private val _uiState = MutableStateFlow(SignupState())
    override val uiState: StateFlow<SignupState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<SignupEffect>()
    val effect: SharedFlow<SignupEffect> = _effect.asSharedFlow()

    override fun onIntent(intent: SignupIntent) {
        when (intent) {
            is SignupIntent.NameChanged -> _uiState.update { it.copy(name = intent.name) }
            is SignupIntent.EmailChanged -> _uiState.update { it.copy(email = intent.email) }
            is SignupIntent.PasswordChanged -> _uiState.update { it.copy(password = intent.password) }
            SignupIntent.SignupClicked -> handleSignup()
            SignupIntent.SigninClicked -> {
                viewModelScope.launch { _effect.emit(SignupEffect.NavigateToLogin) }
            }
        }
    }

    private fun handleSignup() {
        viewModelScope.launch {
            _effect.emit(SignupEffect.NavigateToHome)
        }
    }
}
