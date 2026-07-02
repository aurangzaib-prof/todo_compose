package com.example.todoapp.ui.presentation.onboarding

import com.example.todoapp.base.UiEffect
import com.example.todoapp.base.UiIntent
import com.example.todoapp.base.UiState

data class OnboardingState(
    val currentPage: Int = 0
) : UiState

sealed class OnboardingIntent : UiIntent {
    object NextPage : OnboardingIntent()
}

sealed class OnboardingEffect : UiEffect {
    object NavigateToLogin : OnboardingEffect()
}
