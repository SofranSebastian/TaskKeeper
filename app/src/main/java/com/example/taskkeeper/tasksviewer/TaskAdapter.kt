package com.example.taskkeeper.tasksviewer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskkeeper.database.TaskItem
import com.example.taskkeeper.databinding.ItemTaskLayoutBinding

class TaskAdapter(private val tasksList: MutableList<TaskItem>, val clickListener: TaskListener) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    //class for the view holder which takes as parameter a layout
    class ViewHolder(val binding: ItemTaskLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: TaskListener, item: TaskItem) {
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    //return the View Holder created above with an inflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemTaskLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clickListener, tasksList[position])
    }

    override fun getItemCount() = tasksList.size

    fun setList(list: List<TaskItem>) {
        tasksList.clear()
        tasksList.addAll(list)
        notifyDataSetChanged()
    }
}

class TaskListener(val clickListener: (item: TaskItem) -> Unit) {
    fun onClick(item: TaskItem) = clickListener(item)
}
