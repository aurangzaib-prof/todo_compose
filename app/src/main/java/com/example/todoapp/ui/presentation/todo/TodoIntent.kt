package com.example.todoapp.ui.presentation.todo

import com.example.todoapp.base.UiIntent
import com.example.todoapp.data.local.room.todo_database.TodoEntity
import com.example.todoapp.domain.model.Todo


sealed interface TodoIntent : UiIntent {
    data class TitleChanged(
        val title: String
    ) : TodoIntent


    data class DescriptionChanged(
        val description: String
    ) : TodoIntent
    data class CompletedChanged(
        val isCompleted: Boolean
    ) : TodoIntent

    data class DateSelected(
        val date: Long?
    ) : TodoIntent
    data class TimeSelected(
        val time: String
    ) : TodoIntent
    data class SearchQueryChanged(val query: String) : TodoIntent
    data object SaveTodo : TodoIntent
    data object DeleteTodo : TodoIntent
    data class UpdateTodo(
        val todo: TodoEntity
    ) : TodoIntent
    data class SetTodoForEdit(val todo: TodoEntity) : TodoIntent

    data object Cancel : TodoIntent
}
