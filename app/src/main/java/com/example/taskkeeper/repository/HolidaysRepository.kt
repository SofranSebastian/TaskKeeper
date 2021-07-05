package com.example.taskkeeper.repository

import com.example.taskkeeper.api.RetrofitInstance
import com.example.taskkeeper.ui.holidays.model.HolidayObject
import retrofit2.Response
import javax.inject.Inject

class HolidaysRepository @Inject constructor(
    private val retrofitInstance: RetrofitInstance
){

    suspend fun getHolidays(year: Int, code: String): Response<List<HolidayObject>> {
        return retrofitInstance.api.getHolidays(year, code)
    }

}