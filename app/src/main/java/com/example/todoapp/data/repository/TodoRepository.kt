package com.example.todoapp.data.repository

import com.example.todoapp.data.local.room.todo_database.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun insertTodo(todo: TodoEntity)

    fun getAllTodos(): Flow<List<TodoEntity>>

    suspend fun deleteTodo(todo: TodoEntity)

    suspend fun updateTodo(todo: TodoEntity)
}