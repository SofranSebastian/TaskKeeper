package com.example.taskkeeper.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.taskkeeper.R
import com.example.taskkeeper.databinding.FragmentTaskDetailBinding

class TaskDetailFragment : Fragment() {

    private lateinit var binding: FragmentTaskDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_detail, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val arguments = TaskDetailFragmentArgs.fromBundle(requireArguments())
        binding.title.text = arguments.title

    }
}