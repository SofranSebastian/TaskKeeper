package com.example.taskkeeper.tasksviewer

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.taskkeeper.R

@BindingAdapter("imageResource")
fun ImageView.setImageView(item: TaskItem){
    item?.let{
        setImageResource( when(item.image){
            1 -> R.drawable.ic_android
            2 -> R.drawable.ic_run
            3 -> R.drawable.ic_bike
            else -> R.drawable.ic_empty
        })
    }
}

@BindingAdapter("titleText")
fun TextView.setTitleText(item: TaskItem){
    item?.let{
        text = item.title
    }
}

@BindingAdapter("subtitleText")
fun TextView.setSubtitleText(item: TaskItem){
    item?.let{
        text = item.subtitle
    }
}