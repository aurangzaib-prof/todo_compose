package com.example.todoapp.ui.presentation.todo

import com.example.todoapp.base.UiState
import com.example.todoapp.data.local.room.todo_database.TodoEntity

data class TodoState(
    val todos: List<TodoEntity> = emptyList(),
    val id: Int? = null,
    val title: String = "",
    val description: String? = null,
    val isCompleted: Boolean = false,
    val selectedDate: Long? = null,
    val selectedTime: String? = null,
    val searchQuery: String = ""
) : UiState
