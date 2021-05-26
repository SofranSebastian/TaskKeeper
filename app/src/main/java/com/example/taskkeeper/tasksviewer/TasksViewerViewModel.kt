package com.example.taskkeeper.tasksviewer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TasksViewerViewModel() : ViewModel() {

    private val _tasksList = MutableLiveData<List<TaskItem>>(listOf(
            TaskItem(1,"Task1","Random subtitle."),
            TaskItem(2,"Task2","Random subtitle."),
            TaskItem(3,"Task3","Random subtitle."),
            TaskItem(1,"Task4","Random subtitle."),
            TaskItem(2,"Task5","Random subtitle."),
            TaskItem(0,"Task6","Random subtitle."),
            TaskItem(1,"Task7","Random subtitle."),
            TaskItem(1,"Task8","Random subtitle."),
            TaskItem(3,"Task9","Random subtitle."),
            TaskItem(2,"Task10","Random subtitle.")
    ))
    val tasksList : LiveData<List<TaskItem>>
        get() = _tasksList

    private val _seeMoreClicked = MutableLiveData<Boolean>(false)
    val seeMoreClicked : LiveData<Boolean>
        get() = _seeMoreClicked

    fun onClick(){
        _seeMoreClicked.value?.let{
            _seeMoreClicked.value = !it
        }
    }

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