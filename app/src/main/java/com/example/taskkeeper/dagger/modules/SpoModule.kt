package com.example.taskkeeper.dagger.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import one.space.networking.core.SpoClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SpoModule {

    @Provides
    @Singleton
    fun provideSpoClient(): SpoClient {
        return SpoClient.getDefault()
    }
}