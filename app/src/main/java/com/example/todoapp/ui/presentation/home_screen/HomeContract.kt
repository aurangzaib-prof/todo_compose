package com.example.todoapp.ui.presentation.home_screen

import com.example.todoapp.base.UiEffect
import com.example.todoapp.base.UiIntent
import com.example.todoapp.base.UiState

data class HomeState(
    val username: String = "",
    val email: String = "",
    val isLoading: Boolean = false
) : UiState

sealed class HomeIntent : UiIntent

sealed class HomeEffect : UiEffect