package com.example.taskkeeper.taskdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TaskDetailViewModelFactory(private var title: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskDetailViewModel::class.java)) {
            return TaskDetailViewModel(title) as T
        }
        throw IllegalArgumentException("View Model not found!")
    }
}