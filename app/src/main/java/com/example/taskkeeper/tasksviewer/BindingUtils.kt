package com.example.taskkeeper.tasksviewer

import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.example.taskkeeper.database.TaskItem


@BindingAdapter("titleText")
fun TextView.setTitleText(item: TaskItem) {
    item?.let {
        text = item.title?.toUpperCase()
    }
}

@BindingAdapter("subtitleText")
fun TextView.setSubtitleText(item: TaskItem) {
    item?.let {
        text = item.description
    }
}

@BindingAdapter("cardViewBackground")
fun CardView.setBackgroundCard(item: TaskItem) {
    item?.let {
    }
}

@BindingAdapter("priorityText")
fun TextView.setPriorityText(item: TaskItem) {
    item?.let {
        text = item.priority
    }
}