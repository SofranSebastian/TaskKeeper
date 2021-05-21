package com.example.taskkeeper.tasksviewer

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskkeeper.R
import com.example.taskkeeper.databinding.ItemTaskLayoutBinding

class TaskAdapter(private val tasksList: List<TaskItem>, val clickListener: TaskListener) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    //class for the view holder which takes as parameter a layout
    class ViewHolder(val binding : ItemTaskLayoutBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: TaskListener,  item: TaskItem){
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    //return the View Holder created above with an inflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTaskLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clickListener, tasksList[position])
    }

    override fun getItemCount() = tasksList.size
}

class TaskListener( val clickListener : (title : String) -> Unit){
    fun onClick(item : TaskItem) = clickListener(item.title)
}
