package com.example.taskkeeper.api

import retrofit2.Retrofit
import javax.inject.Inject


class RetrofitInstance @Inject constructor(
    private val retrofit: Retrofit
) {
    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}