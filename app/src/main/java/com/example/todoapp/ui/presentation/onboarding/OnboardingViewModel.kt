package com.example.todoapp.ui.presentation.onboarding

import com.example.todoapp.base.BaseViewModel

class OnboardingViewModel : BaseViewModel<OnboardingState, OnboardingIntent, OnboardingEffect>(
    OnboardingState()
) {

    override fun onIntent(intent: OnboardingIntent) {
        when (intent) {
            OnboardingIntent.NextPage -> handleNextPage()
        }
    }

    private fun handleNextPage() {
        if (currentState.currentPage < 3) {
            updateState { it.copy(currentPage = it.currentPage + 1) }
        } else {
            sendEffect(OnboardingEffect.NavigateToLogin)
        }
    }
}
