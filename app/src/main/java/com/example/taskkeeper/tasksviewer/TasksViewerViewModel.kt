package com.example.taskkeeper.tasksviewer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TasksViewerViewModel(item: List<TaskItem>) : ViewModel() {

    private val _navigateToTaskDetail = MutableLiveData<String>()
    val navigateToTaskDetail
        get() = _navigateToTaskDetail

    fun onTaskClicked(title : String){
        _navigateToTaskDetail.value = title
    }

    fun onTaskDetailNavigated(){
        _navigateToTaskDetail.value = null
    }

}