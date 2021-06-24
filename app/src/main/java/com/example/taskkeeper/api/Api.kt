package com.example.taskkeeper.api

import com.example.taskkeeper.ui.holidays.model.HolidayObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/api/v3/PublicHolidays/{yearChosen}/{countryCodeChosen}")
    suspend fun getHolidays(
        @Path("yearChosen") year: Int,
        @Path("countryCodeChosen") code: String
    ): Response<List<HolidayObject>>

}