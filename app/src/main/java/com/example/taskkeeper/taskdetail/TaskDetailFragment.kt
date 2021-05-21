package com.example.taskkeeper.taskdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.taskkeeper.R
import com.example.taskkeeper.databinding.FragmentTaskDetailBinding

class TaskDetailFragment : Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ) : View? {

        val binding : FragmentTaskDetailBinding = DataBindingUtil.inflate( inflater, R.layout.fragment_task_detail, container, false)

        val arguments = TaskDetailFragmentArgs.fromBundle(requireArguments())

        binding.title.text = arguments.title

        return binding.root

    }

}