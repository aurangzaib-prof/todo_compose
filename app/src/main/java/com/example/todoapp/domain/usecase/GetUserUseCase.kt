package com.example.todoapp.domain.usecase

import com.example.todoapp.data.local.room.auth_database.User
import com.example.todoapp.data.repository.AuthRepository

class GetUserUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): User? {
        return repository.getUser()
    }
}