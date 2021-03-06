package com.example.taskkeeper.tasksviewer

import android.app.Application
import androidx.lifecycle.*
import com.example.taskkeeper.database.Task
import com.example.taskkeeper.database.TaskDatabase
import com.example.taskkeeper.database.TaskItem
import com.example.taskkeeper.database.TaskRepository
import com.example.taskkeeper.utils.toTaskItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository

    //first executed when the TasksViewerViewModel is called
    init {
        val taskDao = TaskDatabase.getDatabase(application).userDao()
        repository = TaskRepository(taskDao)
    }

    private val tasksList: LiveData<List<Task>>
        get() = repository.getAllTasks

    val tasksListMapped: LiveData<List<TaskItem>> = Transformations.map(tasksList) { list ->
        list.map { it.toTaskItem() }
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTasks()
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

    private val _navigateToTaskDetail = MutableLiveData<TaskItem>()
    val navigateToTaskDetail
        get() = _navigateToTaskDetail

    fun onTaskClicked(task: TaskItem) {
        _navigateToTaskDetail.value = task
    }

    fun onTaskDetailNavigated() {
        _navigateToTaskDetail.value = null
    }

}