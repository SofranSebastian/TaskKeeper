package com.example.taskkeeper.networking.spaceone

import retrofit2.Retrofit
import javax.inject.Inject

class SpoInstance @Inject constructor(
    private val retrofit: Retrofit
) {
    val spoApi: SpoApi by lazy{
        retrofit.create(SpoApi::class.java)
    }
}