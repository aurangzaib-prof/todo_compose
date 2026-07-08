package com.example.todoapp.domain.usecase

import com.example.todoapp.data.local.room.todo_database.TodoEntity
import com.example.todoapp.data.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetAllTodosUseCase(
    private val repository: TodoRepository
) {
    operator fun invoke(): Flow<List<TodoEntity>> {
        return repository.getAllTodos()
    }
}