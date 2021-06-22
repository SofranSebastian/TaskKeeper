package com.example.taskkeeper.holidays

import com.example.taskkeeper.api.RetrofitInstance
import com.example.taskkeeper.holidays.model.HolidayObject
import retrofit2.Response

class HolidaysRepository {

    suspend fun getHolidays(year: Int, code: String): Response<List<HolidayObject>> {
        return RetrofitInstance.api.getHolidays(year, code)
    }

}