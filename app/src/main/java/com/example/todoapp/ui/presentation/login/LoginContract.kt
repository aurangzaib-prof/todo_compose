package com.example.todoapp.ui.presentation.login

import com.example.todoapp.base.UiEffect
import com.example.todoapp.base.UiIntent
import com.example.todoapp.base.UiState

data class LoginState(
    val email: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
) : UiState

sealed class LoginIntent : UiIntent {
    data class EmailChanged(val email: String) : LoginIntent()
    object LoginClicked : LoginIntent()
    object SignupClicked : LoginIntent()
}

sealed class LoginEffect : UiEffect {
    object NavigateToHome : LoginEffect()
    object NavigateToSignup : LoginEffect()
}
