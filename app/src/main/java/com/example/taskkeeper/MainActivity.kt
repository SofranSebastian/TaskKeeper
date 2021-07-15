package com.example.taskkeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taskkeeper.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import one.space.networking.core.SpoClient
import one.space.networking.core.SpoClientConfig
import one.space.networking.core.model.SpoAppCredentials

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}