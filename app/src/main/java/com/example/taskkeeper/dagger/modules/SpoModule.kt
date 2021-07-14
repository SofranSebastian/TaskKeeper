package com.example.taskkeeper.dagger.modules

import com.example.taskkeeper.networking.NetworkConnection
import com.example.taskkeeper.networking.spaceone.SpoInstance
import com.example.taskkeeper.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import one.space.networking.core.SpoClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SpoModule {

    @Provides
    @Singleton
    fun provideSpoClient(): SpoClient {
        return SpoClient.getDefault()
    }

    @Provides
    @Singleton
    fun provideSpoInstance(networkConnection: NetworkConnection) =
        SpoInstance(NetworkingModule.provideRetrofitBuilder(networkConnection))


}