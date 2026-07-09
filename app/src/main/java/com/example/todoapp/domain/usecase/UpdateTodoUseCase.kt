package com.example.todoapp.domain.usecase
import com.example.todoapp.data.local.room.todo_database.TodoDao
import com.example.todoapp.data.local.room.todo_database.TodoEntity
import com.example.todoapp.data.repository.TodoRepository

class UpdateTodoUseCase(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(todo: TodoEntity) {
        repository.updateTodo(todo)
    }
}