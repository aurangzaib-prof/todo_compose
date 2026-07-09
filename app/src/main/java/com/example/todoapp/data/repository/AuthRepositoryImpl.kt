package com.example.todoapp.data.repository

import com.example.todoapp.data.local.room.auth_database.AuthDao
import com.example.todoapp.data.local.room.auth_database.User
import com.example.todoapp.data.local.room.auth_database.toUser
import kotlinx.coroutines.flow.firstOrNull

class AuthRepositoryImpl(
    private val authDAO: AuthDao
) :
    AuthRepository(authDAO) {


    override suspend fun getUser(): User? {

        return authDAO.getUser().firstOrNull()?.toUser()

    }
}
