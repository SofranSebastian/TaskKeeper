package com.example.taskkeeper.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskkeeper.database.model.Task

@Database(entities = [Task::class], version = 4, exportSchema = true)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun userDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            val checkInstance = INSTANCE

            if (checkInstance != null) {
                return checkInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }

        }
    }
}