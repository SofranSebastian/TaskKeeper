package com.example.taskkeeper.taskdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskkeeper.database.Task
import com.example.taskkeeper.database.TaskDatabase
import com.example.taskkeeper.database.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository

    //first executed when the TasksViewerViewModel is called
    init {
        val taskDao = TaskDatabase.getDatabase(application).userDao()
        repository = TaskRepository(taskDao)
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }

}