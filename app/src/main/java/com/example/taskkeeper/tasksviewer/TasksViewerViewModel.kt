package com.example.taskkeeper.tasksviewer

import android.app.Application
import androidx.lifecycle.*
import com.example.taskkeeper.database.Task
import com.example.taskkeeper.database.TaskDatabase
import com.example.taskkeeper.database.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : TaskRepository

    private val _tasksList : LiveData<List<Task>>
    val tasksList: LiveData<List<Task>>
        get() = _tasksList

    //first executed when the TasksViewerViewModel is called
    init {
        val taskDao = TaskDatabase.getDatabase(application).userDao()
        repository = TaskRepository(taskDao)
        _tasksList = repository.getAllTasks
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    private val _seeMoreClicked = MutableLiveData<Boolean>(false)
    val seeMoreClicked: LiveData<Boolean>
        get() = _seeMoreClicked

    fun onClick() {
        _seeMoreClicked.value?.let {
            _seeMoreClicked.value = !it
        }
    }

    private val _navigateToTaskDetail = MutableLiveData<String>()
    val navigateToTaskDetail
        get() = _navigateToTaskDetail

    fun onTaskClicked(title: String) {
        _navigateToTaskDetail.value = title
    }

    fun onTaskDetailNavigated() {
        _navigateToTaskDetail.value = null
    }

}