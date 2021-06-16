package com.example.taskkeeper.taskdetail

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskkeeper.R
import com.example.taskkeeper.database.Task
import com.example.taskkeeper.databinding.BottomsheetModalUpdateFormBinding
import com.example.taskkeeper.utils.toTaskItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragmentUpdate(private val individualTask: Task) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetModalUpdateFormBinding
    private val taskDetailViewModel: TaskDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottomsheet_modal_update_form,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.titleTextInput.setText(individualTask.toTaskItem().title)
        binding.descriptionTextInput.setText(individualTask.toTaskItem().description)
        binding.spinner.setSelection(getPosition(individualTask.toTaskItem().priority))

        binding.buttonUpdate.setOnClickListener {
            if (insertData()) {
                dismiss()
                this.findNavController()
                    .navigate(TaskDetailFragmentDirections.navigateToTasksViewer())
            }
        }
    }

    private fun insertData(): Boolean {
        val title = binding.titleTextInput.text.toString()
        val description = binding.descriptionTextInput.text.toString()
        val priority = binding.spinner.selectedItem.toString()

        return if (checkInput(title, description, priority)) {
            val task = Task(
                id = individualTask.id,
                title = title,
                description = description,
                priority = priority
            )
            taskDetailViewModel.updateTask(task)
            true
        } else {
            Toast.makeText(
                requireContext(),
                "Complete all required fields",
                Toast.LENGTH_SHORT
            ).show()
            false
        }
    }

    private fun checkInput(title: String, description: String, priority: String): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }

    private fun getPosition(priority: String?): Int {
        return when (priority) {
            "HIGH" -> 0
            "MODERATE" -> 1
            else -> 2
        }
    }
}