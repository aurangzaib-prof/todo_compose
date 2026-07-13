package com.example.todoapp.ui.presentation.task_screen

import androidx.lifecycle.viewModelScope
import com.example.todoapp.base.BaseViewModel
import com.example.todoapp.data.local.room.todo_database.TodoEntity
import com.example.todoapp.data.repository.MainRepository
import com.example.todoapp.ui.presentation.todo.TodoEffect
import com.example.todoapp.ui.presentation.todo.TodoIntent
import com.example.todoapp.ui.presentation.todo.TodoState
import com.example.todoapp.ui.extensions.toFormattedDate
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class TodoViewModel(
    private val repository: MainRepository
) : BaseViewModel<TodoState, TodoIntent, TodoEffect>(
    TodoState()
) {

    init {
        getAllTodos()
    }

    override suspend fun onIntent(intent: TodoIntent) {

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
                    TodoEffect.ShowToast("Date selected: ${intent.date.toFormattedDate()}")
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

            is TodoIntent.SearchQueryChanged -> {
                updateState {
                    it.copy(searchQuery = intent.query)
                }
            }

            TodoIntent.SaveTodo -> {
                saveTodo()
            }

            TodoIntent.DeleteTodo -> {
                deleteTodo()
            }

            is TodoIntent.SetTodoForEdit -> {
                updateState {
                    it.copy(
                        id = intent.todo.id,
                        title = intent.todo.title,
                        description = intent.todo.description,
                        isCompleted = intent.todo.isCompleted,
                        selectedDate = intent.todo.date,
                        selectedTime = intent.todo.time
                    )
                }
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
            combine(repository.getAllTodos(), uiState) { todos, state ->
                if (state.searchQuery.isBlank()) {
                    todos
                } else {
                    todos.filter {
                        it.title.contains(state.searchQuery, ignoreCase = true) ||
                                (it.description?.contains(state.searchQuery, ignoreCase = true) ?: false)
                    }
                }
            }.collect { filteredTodos ->
                updateState { state ->
                    state.copy(
                        todos = filteredTodos
                    )
                }
            }
        }
    }


    private fun saveTodo() {

        val todo = TodoEntity(
            id = currentState.id ?: 0,
            title = currentState.title,
            description = currentState.description ?: "",
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

            if (currentState.id != null) {
                repository.updateTodo(todo)
                sendEffect(TodoEffect.ShowToast("Todo updated successfully"))
            } else {
                repository.insertTodo(todo)
                sendEffect(TodoEffect.ShowToast("Todo saved successfully"))
            }
            updateState { TodoState() }
        }
    }

    private fun deleteTodo() {
        val todo = TodoEntity(
            id = currentState.id ?: return,
            title = currentState.title,
            description = currentState.description ?: "",
            isCompleted = currentState.isCompleted,
            date = currentState.selectedDate,
            time = currentState.selectedTime
        )
        viewModelScope.launch {
            repository.deleteTodo(todo)
            sendEffect(TodoEffect.ShowToast("Todo deleted successfully"))
            updateState { TodoState() }
        }
    }
}
