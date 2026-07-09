package com.example.todoapp.ui.presentation.calender_screen

import androidx.lifecycle.viewModelScope
import com.example.todoapp.base.BaseViewModel
import com.example.todoapp.data.local.room.todo_database.TodoEntity
import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.usecase.AddTodoUseCase
import com.example.todoapp.domain.usecase.UpdateTodoUseCase
import com.example.todoapp.ui.presentation.todo.TodoEffect
import com.example.todoapp.ui.presentation.todo.TodoIntent
import com.example.todoapp.ui.presentation.todo.TodoState
import com.example.todoapp.ui.utils.Utils.formatDate
import kotlinx.coroutines.launch

class CalenderViewModel(
    private val addTodoUseCase: AddTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase
) : BaseViewModel<TodoState, TodoIntent, TodoEffect>(
    TodoState()
) {
    override fun onIntent(intent: TodoIntent) {

        when (intent) {

            is TodoIntent.DateSelected -> {
                updateState {
                    it.copy(selectedDate = intent.date)
                }
                sendEffect(
                    TodoEffect.ShowToast("Date selected: ${formatDate(intent.date)}")
                )
            }


            is TodoIntent.TitleChanged -> {
                updateState {
                    it.copy(
                        title = intent.title
                    )
                }
            }

            is TodoIntent.CompletedChanged -> {
                updateState {
                    it.copy(
                        isCompleted = intent.isCompleted
                    )
                }
            }

            is TodoIntent.UpdateTodo -> {
                updateTodo(intent.todo)
            }

            is TodoIntent.SaveTodo -> {
                saveTodo()
            }


            TodoIntent.Cancel -> {
                updateState {
                    TodoState()
                }
            }

            else -> {}
        }
    }

    private fun saveTodo() {

        val todo = TodoEntity(
            title = currentState.title,
            description = currentState.description,
            isCompleted = currentState.isCompleted,
            date = currentState.selectedDate,
            time = currentState.selectedTime
        )
        viewModelScope.launch {
            when {
                todo.title.isBlank() -> {
                    sendEffect(TodoEffect.ShowToast("Title is required"))
                    return@launch
                }

                todo.date == null -> {
                    sendEffect(TodoEffect.ShowToast("Please select a date"))
                    return@launch
                }

            }

            addTodoUseCase(todo)
            sendEffect(TodoEffect.ShowToast("Todo saved successfully"))
        }
    }

    private fun updateTodo(todo: TodoEntity) {

//        val todo = TodoEntity(
//            title = currentState.title,
//            description = currentState.description,
//            isCompleted = currentState.isCompleted,
//            date = currentState.selectedDate,
//            time = currentState.selectedTime
//        )
        viewModelScope.launch {
//            when {
//                todo.title.isBlank() -> {
//                    sendEffect(TodoEffect.ShowToast("Title is required"))
//                    return@launch
//                }
//
//                todo.date == null -> {
//                    sendEffect(TodoEffect.ShowToast("Please select a date"))
//                    return@launch
//                }
//
//            }
            sendEffect(TodoEffect.ShowToast("Todo updated successfully"))
            updateTodoUseCase(todo)

        }
    }
}