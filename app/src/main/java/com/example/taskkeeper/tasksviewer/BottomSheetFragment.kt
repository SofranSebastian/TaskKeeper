package com.example.taskkeeper.tasksviewer

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.taskkeeper.R
import com.example.taskkeeper.database.Task
import com.example.taskkeeper.databinding.BottomsheetModalAddFormBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetModalAddFormBinding
    private val tasksViewerViewModel: TasksViewerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.bottomsheet_modal_add_form, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonAdd.setOnClickListener {
            if (insertData()) {
                dismiss()
            }
        }
    }

    private fun insertData(): Boolean {
        val title = binding.titleTextInput.text.toString()
        val description = binding.descriptionTextInput.text.toString()
        val priority = binding.spinner.selectedItem.toString()

        return if (checkInput(title, description)) {
            val task = Task(id = 0, title = title, description = description, priority = priority)
            tasksViewerViewModel.addTask(task)
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

    private fun checkInput(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }
}