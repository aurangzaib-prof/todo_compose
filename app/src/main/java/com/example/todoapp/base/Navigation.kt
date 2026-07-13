package com.example.todoapp.base

import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object OnboardingOne

@Serializable
object Login

@Serializable
object Signup

@Serializable
object Home

@Serializable
data class TaskDetail(val todoJson: String)
