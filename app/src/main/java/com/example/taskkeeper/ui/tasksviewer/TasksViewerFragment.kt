package com.example.taskkeeper.ui.tasksviewer

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskkeeper.R
import com.example.taskkeeper.databinding.FragmentTasksViewerBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import javax.inject.Inject

@AndroidEntryPoint
class TasksViewerFragment : Fragment() {

    private lateinit var binding: FragmentTasksViewerBinding
    private val tasksViewerViewModel: TasksViewerViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    //variables for the animations types
    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(context, R.anim.rotate_open_animation)
    }

    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(context, R.anim.rotate_close_animation)
    }

    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(context, R.anim.from_bottom_animation)
    }

    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(context, R.anim.to_bottom_animation)
    }

    //method for setting the visibility of the FAB buttons
    private fun setVisibility(clicked: Boolean) {
        if (clicked) {
            binding.addTaskButton.visibility = View.VISIBLE
            binding.deleteAllButton.visibility = View.VISIBLE
        } else {
            binding.addTaskButton.visibility = View.INVISIBLE
            binding.deleteAllButton.visibility = View.GONE
        }
    }

    //method for setting the animation of the FAB buttons
    private fun setAnimation(clicked: Boolean) {
        if (clicked) {
            binding.seeMoreButton.startAnimation(rotateOpen)
            binding.addTaskButton.startAnimation(fromBottom)
            binding.deleteAllButton.startAnimation(fromBottom)
        } else {
            binding.seeMoreButton.startAnimation(rotateClose)
            binding.addTaskButton.startAnimation(toBottom)
            binding.deleteAllButton.startAnimation(toBottom)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //reference for the binding object
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tasks_viewer, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //binding the view model to the layout
        binding.tasksViewerViewModel = tasksViewerViewModel

        //create the adapter
        adapter = TaskAdapter(mutableListOf(), TaskListener { it ->
            tasksViewerViewModel.onTaskClicked(it)
        })

        //observer for the tasksList from the VM
        tasksViewerViewModel.tasksListMapped.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }

        //bindings for the RecyclerView
        binding.tasksList.adapter = adapter
        binding.tasksList.layoutManager = LinearLayoutManager(this.context)

        //observer for the navigation to the TaskDetailFragment
        tasksViewerViewModel.navigateToTaskDetail.observe(viewLifecycleOwner) {
            it?.let {
                this.findNavController()
                    .navigate(TasksViewerFragmentDirections.navigateToTaskDetail(it))
                tasksViewerViewModel.onTaskDetailNavigated()
            }
        }

        //observer for the click listener for the 'See More' button
        tasksViewerViewModel.seeMoreClicked.observe(viewLifecycleOwner) {
            setVisibility(it)
            setAnimation(it)
        }

        //dummy click listeners for the 'Delete All' and 'Add' FAB's
        binding.deleteAllButton.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())

            dialogBuilder.setPositiveButton("Yes") { _, _ ->
                tasksViewerViewModel.deleteAllTasks()
            }

            dialogBuilder.setNegativeButton("No") { _, _ ->
            }

            dialogBuilder.setTitle("Delete all tasks")

            dialogBuilder.setMessage("Do you want to delete all tasks?")

            dialogBuilder.create().show()
        }

        binding.addTaskButton.setOnClickListener {
            val bottomSheetFragment = BottomSheetAddFragment()
            bottomSheetFragment.show(parentFragmentManager, "BottomSheetDialog")
        }

        val bnv = binding.bottomNavigationView
        val navController = this.findNavController()
        bnv.setupWithNavController(navController)

    }
}