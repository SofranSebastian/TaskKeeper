package com.example.taskkeeper.ui.holidays.model

data class HolidayObject(
    val date: String,
    val localName: String,
    val name: String,
    val countryCode: String,
    val fixed: Boolean,
    val global: Boolean
)