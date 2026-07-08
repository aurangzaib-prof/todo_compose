package com.example.todoapp.data.local.room.todo_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert
    suspend fun insertTodo(todo: TodoEntity)

    @Query("SELECT * FROM todos")
    fun getAllTodos(): Flow<List<TodoEntity>>

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)

    @Update
    suspend fun updateTodo(todo: TodoEntity)
}