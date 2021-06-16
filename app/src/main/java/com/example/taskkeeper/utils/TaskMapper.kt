package com.example.taskkeeper.utils

import com.example.taskkeeper.database.Task
import com.example.taskkeeper.database.TaskItem

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


