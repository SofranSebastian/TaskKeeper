package com.example.taskkeeper.dagger.modules

import android.content.Context
import com.example.taskkeeper.networking.NetworkConnection
import com.example.taskkeeper.repository.HolidaysRepository
import com.example.taskkeeper.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideHolidaysRepository(networkConnection: NetworkConnection) =
        HolidaysRepository(NetworkingModule.provideRetrofitInstance(networkConnection))

    @Provides
    @Singleton
    fun provideTasksRepository(@ApplicationContext context: Context) =
        TaskRepository(DatabaseModule.provideTaskDao(context))
}