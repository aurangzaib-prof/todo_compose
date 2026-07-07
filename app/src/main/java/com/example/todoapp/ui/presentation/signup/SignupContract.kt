package com.example.todoapp.ui.presentation.signup

import com.example.todoapp.base.UiEffect
import com.example.todoapp.base.UiIntent
import com.example.todoapp.base.UiState

data class SignupState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    var isLoading: Boolean = false,
    val error: String? = null
) : UiState


sealed class SignupIntent : UiIntent {
    data class NameChanged(val name: String) : SignupIntent()
    data class EmailChanged(val email: String) : SignupIntent()
    data class PasswordChanged(val password: String) : SignupIntent()
    object SignupClicked : SignupIntent()
    object SigninClicked : SignupIntent()
}

sealed class SignupEffect : UiEffect {
    object NavigateToLogin : SignupEffect()
    object NavigateToHome : SignupEffect()
}
