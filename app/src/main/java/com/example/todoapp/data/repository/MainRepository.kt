package com.example.todoapp.data.repository

import com.example.todoapp.data.local.room.auth_database.AuthEntity
import com.example.todoapp.data.local.room.auth_database.User
import com.example.todoapp.data.local.room.todo_database.TodoEntity
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    //    -------------  TODO Auth Section -------------
    suspend fun register(authEntity: AuthEntity): Result<Unit>


    suspend fun getUserByEmail(email: String): AuthEntity?

    suspend fun login(
        email: String,
        password: String
    ): Result<AuthEntity>


    suspend fun getUser(): User?

//    -------------  TODO Task Section -------------


    suspend fun insertTodo(todo: TodoEntity)

    fun getAllTodos(): Flow<List<TodoEntity>>

    suspend fun deleteTodo(todo: TodoEntity)

    suspend fun updateTodo(todo: TodoEntity)
}