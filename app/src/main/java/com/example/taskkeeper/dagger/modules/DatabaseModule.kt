package com.example.taskkeeper.dagger.modules

import android.content.Context
import com.example.taskkeeper.database.TaskDao
import com.example.taskkeeper.database.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTaskDao(@ApplicationContext context: Context): TaskDao {
        return TaskDatabase.getDatabase(context).userDao()
    }
}