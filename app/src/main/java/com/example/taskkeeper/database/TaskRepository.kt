package com.example.taskkeeper.database

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {
    val getAllTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }
}