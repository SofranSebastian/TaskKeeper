package com.example.taskkeeper.tasksviewer

import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskkeeper.databinding.FragmentTasksViewerBinding
import com.example.taskkeeper.R
import com.example.taskkeeper.taskdetail.TaskDetailFragment

class TasksViewerFragment : Fragment() {

    private lateinit var binding : FragmentTasksViewerBinding
    private val tasksList = mutableListOf<TaskItem>()

    //variables for the animations types
    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(context, R.anim.rotate_open_animation)
    }

    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(context, R.anim.rotate_close_animation)
    }

    private val fromBottom: Animation by lazy{
        AnimationUtils.loadAnimation(context, R.anim.from_bottom_animation)
    }

    private val toBottom: Animation by lazy{
        AnimationUtils.loadAnimation(context, R.anim.to_bottom_animation)
    }

    //method for setting the visibility of the FAB buttons
    private fun setVisibility(clicked: Boolean){
        if(clicked){
            binding.addTaskButton.visibility = View.VISIBLE
            binding.deleteAllButton.visibility = View.VISIBLE
        }else{
            binding.addTaskButton.visibility = View.INVISIBLE
            binding.deleteAllButton.visibility = View.GONE
        }
    }

    //method for setting the animation of the FAB buttons
    private fun setAnimation(clicked: Boolean){
        if(clicked){
            binding.seeMoreButton.startAnimation(rotateOpen)
            binding.addTaskButton.startAnimation(fromBottom)
            binding.deleteAllButton.startAnimation(fromBottom)
        }else{
            binding.seeMoreButton.startAnimation(rotateClose)
            binding.addTaskButton.startAnimation(toBottom)
            binding.deleteAllButton.startAnimation(toBottom)
        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ) : View? {

        //reference for the binding object
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_tasks_viewer, container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //create the VM
        val tasksViewerViewModel : TasksViewerViewModel by viewModels()

        //binding the view model to the layout
        binding.tasksViewerViewModel = tasksViewerViewModel

        //observer for the tasksList from the VM
        tasksViewerViewModel.tasksList.observe(viewLifecycleOwner, Observer {
            it -> it?.let{
                tasksList.clear()
                tasksList.addAll(it)
            }
        })

        //create the adapter
        val adapter = TaskAdapter(tasksList, TaskListener { it ->
            tasksViewerViewModel.onTaskClicked(it)
        })

        //bindings for the RecyclerView
        binding.tasksList.adapter = adapter
        binding.tasksList.layoutManager = LinearLayoutManager(this.context)

        //observer for the navigation to the TaskDetailFragment
        tasksViewerViewModel.navigateToTaskDetail.observe(viewLifecycleOwner, Observer{
                it -> it?.let{
                this.findNavController().navigate(
                        TasksViewerFragmentDirections.navigateToTaskDetail(it)
                )
                tasksViewerViewModel.onTaskDetailNavigated()
            }
        })

        //observer for the click listener for the 'See More' button
        tasksViewerViewModel.seeMoreClicked.observe(viewLifecycleOwner, Observer {
                it-> it?.let{
                setVisibility(it)
                setAnimation(it)
            }
        })

        //dummy click listeners for the 'Delete All' and 'Add' FAB's
        binding.deleteAllButton.setOnClickListener {
            Toast.makeText(context,"Am apasat pe delete all!", Toast.LENGTH_SHORT).show()
        }

        binding.addTaskButton.setOnClickListener {
            Toast.makeText(context,"Am apasat pe add!", Toast.LENGTH_SHORT).show()
        }
    }
}