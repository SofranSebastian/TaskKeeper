package com.example.taskkeeper.taskdetail

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskkeeper.R
import com.example.taskkeeper.databinding.FragmentTaskDetailBinding
import com.example.taskkeeper.utils.toTask

class TaskDetailFragment : Fragment() {

    private lateinit var binding: FragmentTaskDetailBinding
    private val taskDetailViewModel: TaskDetailViewModel by viewModels()
    private lateinit var arguments: TaskDetailFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_detail, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.taskDetailViewModel = taskDetailViewModel
        arguments = TaskDetailFragmentArgs.fromBundle(requireArguments())

        binding.title.text = arguments.item.title
        binding.taskDescription.text = arguments.item.description.toString()

        //Add menu
        setHasOptionsMenu(true)

        binding.backButton.setOnClickListener {
            this.findNavController().navigate(TaskDetailFragmentDirections.navigateToTasksViewer())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //delete button from menu
        if (item.itemId == R.id.menu_delete) {
            deleteTask()
        }

        //update button from menu
        if (item.itemId == R.id.menu_update) {
            updateTask()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteTask() {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        dialogBuilder.setPositiveButton("Yes") { _, _ ->
            taskDetailViewModel.deleteTask(arguments.item.toTask())
            this.findNavController().navigate(TaskDetailFragmentDirections.navigateToTasksViewer())
        }

        dialogBuilder.setNegativeButton("No") { _, _ ->
        }

        dialogBuilder.setTitle("Delete task")

        dialogBuilder.setMessage("Do you want to delete this task?")

        dialogBuilder.create().show()
    }

    private fun updateTask() {
        val bottomSheetFragment = BottomSheetUpdateFragment(arguments.item.toTask())
        bottomSheetFragment.show(parentFragmentManager, "BottomSheetDialogUpdate")
    }
}