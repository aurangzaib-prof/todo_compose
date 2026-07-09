package com.example.todoapp.data.mapper

import com.example.todoapp.data.local.room.auth_database.AuthEntity
import com.example.todoapp.data.local.room.auth_database.User

fun AuthEntity.toDomain(): User {
    return User(
        username = name,
        email = email
    )
}