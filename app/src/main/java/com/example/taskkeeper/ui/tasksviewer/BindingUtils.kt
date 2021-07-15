package com.example.taskkeeper.ui.tasksviewer

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.taskkeeper.database.model.TaskItem

@BindingAdapter("titleText")
fun TextView.setTitleText(item: TaskItem) {
    text = item.title?.toUpperCase()
}

@BindingAdapter("subtitleText")
fun TextView.setSubtitleText(item: TaskItem) {
    text = item.description
}

@BindingAdapter("priorityText")
fun TextView.setPriorityText(item: TaskItem) {
    text = item.priority
}