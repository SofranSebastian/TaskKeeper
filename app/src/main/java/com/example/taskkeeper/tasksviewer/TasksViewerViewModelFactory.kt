package com.example.taskkeeper.tasksviewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class TasksViewerViewModelFactory( private var tasksList : List<TaskItem>) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TasksViewerViewModel::class.java)){
            return TasksViewerViewModel(tasksList) as T
        }
        throw IllegalArgumentException("View Model not found!")
    }
}