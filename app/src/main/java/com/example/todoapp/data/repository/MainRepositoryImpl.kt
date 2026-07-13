package com.example.todoapp.data.repository

import com.example.todoapp.data.local.room.auth_database.AuthDao
import com.example.todoapp.data.local.room.auth_database.AuthEntity
import com.example.todoapp.data.local.room.auth_database.User
import com.example.todoapp.data.local.room.auth_database.toUser
import com.example.todoapp.data.local.room.todo_database.TodoDao
import com.example.todoapp.data.local.room.todo_database.TodoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class MainRepositoryImpl(
    private val todoDao: TodoDao,
    private val authDao: AuthDao
) : MainRepository {

    //------------------TODO Task Dao Section------------------

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


    //------------------TODO Auth Dao Section------------------

    override suspend fun register(authEntity: AuthEntity): Result<Unit> {
        return try {
            authDao.registerUser(authEntity)
            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserByEmail(email: String): AuthEntity? {
        return authDao.getUserByEmail(email)
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<AuthEntity> {

        val user = authDao.getUserByEmail(email)

        return if (user != null && user.password == password) {
            Result.success(user)
        } else {
            Result.failure(Exception("Invalid email or password"))
        }
    }

    override suspend fun getUser(): User? {
        return authDao.getUser()
            .firstOrNull()
            ?.toUser()
    }

}