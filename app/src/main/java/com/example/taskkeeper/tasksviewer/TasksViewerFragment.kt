package com.example.taskkeeper.tasksviewer

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskkeeper.databinding.FragmentTasksViewerBinding
import com.example.taskkeeper.R
import com.example.taskkeeper.taskdetail.TaskDetailFragment

class TasksViewerFragment : Fragment() {

    private var tasksList : List<TaskItem> = listOf(
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
                                                    )

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ) : View? {

        //reference for the binding object
        val binding : FragmentTasksViewerBinding = DataBindingUtil.inflate( inflater, R.layout.fragment_tasks_viewer, container,false)

        //creating view model and view model factory
        val viewModelFactory = TasksViewerViewModelFactory(tasksList)
        val tasksViewerViewModel = ViewModelProvider(this, viewModelFactory)
                        .get(TasksViewerViewModel::class.java)

        //binding the view model to the layout
        binding.tasksViewerViewModel = tasksViewerViewModel

        val adapter = TaskAdapter(tasksList, TaskListener { it ->
            tasksViewerViewModel.onTaskClicked(it)
        })


        binding.tasksList.adapter = adapter
        binding.tasksList.layoutManager = LinearLayoutManager(this.context)


        tasksViewerViewModel.navigateToTaskDetail.observe(viewLifecycleOwner, Observer{
                it -> it?.let{
                    this.findNavController().navigate(
                        TasksViewerFragmentDirections.navigateToTaskDetail(it)
                    )
                    tasksViewerViewModel.onTaskDetailNavigated()
                }
        })

        return binding.root
    }

}