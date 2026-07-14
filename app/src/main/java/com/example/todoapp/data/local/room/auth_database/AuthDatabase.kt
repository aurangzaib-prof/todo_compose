package com.example.todoapp.data.local.room.auth_database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AuthEntity::class], version = 1, exportSchema = false)
abstract class AuthDatabase : RoomDatabase() {
    abstract fun userDao(): AuthDao
}