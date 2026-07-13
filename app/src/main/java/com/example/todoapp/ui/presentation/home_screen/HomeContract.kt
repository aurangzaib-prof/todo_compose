package com.example.todoapp.ui.presentation.home_screen

import com.example.todoapp.base.UiEffect
import com.example.todoapp.base.UiIntent
import com.example.todoapp.base.UiState
import com.example.todoapp.data.local.room.todo_database.TodoEntity

data class HomeState(
    val username: String = "",
    val email: String = "",
    val completedTodos: List<TodoEntity> = emptyList(),
    val incompletedTodos: List<TodoEntity> = emptyList(),
    val isLoading: Boolean = false
) : UiState

sealed class HomeIntent : UiIntent {
    data class TodoClicked(val todo: TodoEntity) : HomeIntent()
}

sealed class HomeEffect : UiEffect {
    data class NavigateToDetails(val todo: TodoEntity) : HomeEffect()
}
