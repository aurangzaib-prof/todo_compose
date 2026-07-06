package com.example.todoapp.domain.model

sealed class StartDestination {

    object Onboarding : StartDestination()

    object Login : StartDestination()

    object Home : StartDestination()
}