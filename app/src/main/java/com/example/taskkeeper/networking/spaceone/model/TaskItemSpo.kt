package com.example.taskkeeper.networking.spaceone.model

import one.space.networking.core.annotations.SpoEntity
import one.space.networking.core.annotations.SpoItemId

@SpoEntity("task")
data class TaskItemSpo(
    @SpoItemId
    var id: Long,
    var title: String,
    var description: String,
    var priority: String
)