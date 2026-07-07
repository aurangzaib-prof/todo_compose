package com.example.todoapp.domain.usecase

import com.example.todoapp.domain.UserPreferenceRepository
import com.example.todoapp.domain.model.StartDestination

class GetStartDestinationUseCase(
    private val repository: UserPreferenceRepository
)
{
    suspend operator fun invoke(): StartDestination {

        val onboarding =
            repository.isOnboardingCompleted()
        val login =
            repository.isLoggedIn()

        return when {
            !onboarding ->
                StartDestination.Onboarding

            !login ->
                StartDestination.Login

            else ->
                StartDestination.Home
        }
    }
}