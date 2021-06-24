package com.example.taskkeeper.database.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaskItem(
    val id: Int?,
    val title: String?,
    val description: String?,
    val priority: String?
) : Parcelable


