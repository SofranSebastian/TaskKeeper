package com.example.taskkeeper.ui.tasksviewer

import android.util.Log
import androidx.lifecycle.*
import com.example.taskkeeper.database.model.Task
import com.example.taskkeeper.database.model.TaskItem
import com.example.taskkeeper.mapper.toTaskItem
import com.example.taskkeeper.networking.spaceone.model.TaskItemSpo
import com.example.taskkeeper.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import one.space.networking.core.SpoClient
import javax.inject.Inject

@HiltViewModel
class TasksViewerViewModel @Inject constructor(
    private var repository: TaskRepository,
    private val spoClient: SpoClient
) : ViewModel() {

    private val tasksList: LiveData<List<Task>>
        get() = repository.getAllTasks

    val tasksListMapped: LiveData<List<TaskItem>> = Transformations.map(tasksList) { list ->
        list.map { it.toTaskItem() }
    }

    fun addTask(task: TaskItemSpo) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = spoClient.item()
                    .createItem(task)
            if(response.isSuccessful){
                Log.i("SpoResponse", "success")
            }else{
                Log.i("SpoResponse", "failed")
            }

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