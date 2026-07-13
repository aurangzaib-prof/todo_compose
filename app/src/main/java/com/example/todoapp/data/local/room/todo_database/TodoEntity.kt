package com.example.todoapp.data.local.room.todo_database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoapp.utils.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.TODOS_TABLE_NAME)
data class TodoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String?,

    val isCompleted: Boolean = false,

    // store date as millis
    val date: Long?,

    // store formatted time
    val time: String?
)