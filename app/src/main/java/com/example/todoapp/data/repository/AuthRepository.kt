package com.example.todoapp.data.repository

import com.example.todoapp.data.local.room.User
import com.example.todoapp.data.local.room.UserDao

class AuthRepository(private val userDao: UserDao) {
    suspend fun register(user: User): Result<Unit> {
        return try {
            userDao.registerUser(user)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<User> {
        val user = userDao.getUserByEmail(email)
        return if (user != null && user.password == password) {
            Result.success(user)
        } else {
            Result.failure(Exception("Invalid email or password"))
        }
    }
}
