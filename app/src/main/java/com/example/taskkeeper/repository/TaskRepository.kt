package com.example.taskkeeper.repository

import androidx.lifecycle.LiveData
import com.example.taskkeeper.database.TaskDao
import com.example.taskkeeper.database.model.Task

class TaskRepository(private val taskDao: TaskDao) {
    val getAllTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }
}