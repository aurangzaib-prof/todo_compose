package com.example.todoapp.data.repository

import com.example.todoapp.data.local.room.todo_database.TodoDao
import com.example.todoapp.data.local.room.todo_database.TodoEntity
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val todoDao: TodoDao
) : TodoRepository {

    override suspend fun insertTodo(todo: TodoEntity) {
        todoDao.insertTodo(todo)
    }

    override fun getAllTodos(): Flow<List<TodoEntity>> {
        return todoDao.getAllTodos()
    }

    override suspend fun deleteTodo(todo: TodoEntity) {
        todoDao.deleteTodo(todo)
    }

    override suspend fun updateTodo(todo: TodoEntity) {
        todoDao.updateTodo(todo)
    }
}