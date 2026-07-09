package com.example.todoapp.data.repository

import com.example.todoapp.data.local.room.auth_database.AuthEntity
import com.example.todoapp.data.local.room.auth_database.AuthDao
import com.example.todoapp.data.local.room.auth_database.User
import com.example.todoapp.data.local.room.auth_database.toUser
import com.example.todoapp.data.local.room.todo_database.TodoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

open class AuthRepository(
    private val authDao: AuthDao
) {

    open suspend fun register(authEntity: AuthEntity): Result<Unit> {

        return try {
            authDao.registerUser(authEntity)
            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    open suspend fun getUserByEmail(email: String): AuthEntity? {
        return authDao.getUserByEmail(email)
    }

    open suspend fun login(
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


    open suspend fun getUser(): User? {

        return authDao.getUser()
            .firstOrNull()
            ?.toUser()

    }
}