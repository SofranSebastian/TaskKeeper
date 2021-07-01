package com.example.taskkeeper.mapper

import com.example.taskkeeper.database.model.Task
import com.example.taskkeeper.database.model.TaskItem

fun Task.toTaskItem() = TaskItem(
    id = id,
    title = title,
    description = description,
    priority = priority
)

fun TaskItem.toTask() = Task(
    id = id!!,
    title = title!!,
    description = description!!,
    priority = priority!!
)


