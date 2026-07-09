package com.example.todoapp.ui.presentation.todo

import com.example.todoapp.base.UiIntent
import com.example.todoapp.data.local.room.todo_database.TodoEntity
import com.example.todoapp.domain.model.Todo


sealed interface TodoIntent : UiIntent {

    // Text field changes
    data class TitleChanged(
        val title: String
    ) : TodoIntent


    data class DescriptionChanged(
        val description: String
    ) : TodoIntent


    // Checkbox change
    data class CompletedChanged(
        val isCompleted: Boolean
    ) : TodoIntent


    // Date picker
    data class DateSelected(
        val date: Long?
    ) : TodoIntent


    // Time picker
    data class TimeSelected(
        val time: String
    ) : TodoIntent


    // Button actions
    data object SaveTodo : TodoIntent
    data class UpdateTodo(
        val todo: TodoEntity
    ) : TodoIntent

    data object Cancel : TodoIntent
}