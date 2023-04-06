package com.example.tutorial5.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tutorial5.database.daos.TodoDao
import com.example.tutorial5.database.entities.Todo
import java.time.Instant

@Database(entities =[Todo::class], version = 1 )
abstract class TodoDatabase:RoomDatabase() {
    abstract fun getTodoDao():TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null
        fun getInstance(context: Context): TodoDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_db"
                ).build().also {
                    INSTANCE = it
                }

            }
        }
    }
}