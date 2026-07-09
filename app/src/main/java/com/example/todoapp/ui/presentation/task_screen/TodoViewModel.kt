package com.example.todoapp.ui.presentation.task_screen

import androidx.lifecycle.viewModelScope
import com.example.todoapp.base.BaseViewModel
import com.example.todoapp.data.local.room.todo_database.TodoEntity
import com.example.todoapp.domain.usecase.AddTodoUseCase
import com.example.todoapp.domain.usecase.GetAllTodosUseCase
import com.example.todoapp.ui.presentation.todo.TodoEffect
import com.example.todoapp.ui.presentation.todo.TodoIntent
import com.example.todoapp.ui.presentation.todo.TodoState
import com.example.todoapp.ui.utils.Utils.formatDate
import kotlinx.coroutines.launch

class TodoViewModel(
    private val addTodoUseCase: AddTodoUseCase,
    private val getAllTodosUseCase: GetAllTodosUseCase
) : BaseViewModel<TodoState, TodoIntent, TodoEffect>(
    TodoState()
) {

    init {
        getAllTodos()
    }

    override fun onIntent(intent: TodoIntent) {

        when (intent) {

            is TodoIntent.TitleChanged -> {
                updateState {
                    it.copy(
                        title = intent.title
                    )
                }
            }

            is TodoIntent.TimeSelected -> {
                updateState {
                    it.copy(selectedTime = intent.time)
                }

                sendEffect(
                    TodoEffect.ShowToast("Time selected: ${intent.time}")
                )
            }

            is TodoIntent.DateSelected -> {
                updateState {
                    it.copy(selectedDate = intent.date)
                }

                sendEffect(
                    TodoEffect.ShowToast("Date selected: ${formatDate(intent.date)}")
                )
            }


            is TodoIntent.DescriptionChanged -> {
                updateState {
                    it.copy(
                        description = intent.description
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

            TodoIntent.SaveTodo -> {
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

    private fun getAllTodos() {
        viewModelScope.launch {
            getAllTodosUseCase().collect { todos ->
                updateState { state ->
                    state.copy(
                        todos = todos
                    )
                }
            }
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

                todo.time.isNullOrBlank() -> {
                    sendEffect(TodoEffect.ShowToast("Please select a time"))
                    return@launch
                }
            }

            addTodoUseCase(todo)
            sendEffect(TodoEffect.ShowToast("Todo saved successfully"))
        }
    }
}