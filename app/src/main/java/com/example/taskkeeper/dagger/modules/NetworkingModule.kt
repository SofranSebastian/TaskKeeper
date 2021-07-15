package com.example.taskkeeper.dagger.modules

import com.example.taskkeeper.api.RetrofitInstance
import com.example.taskkeeper.networking.NetworkConnection
import com.example.taskkeeper.utils.Constants
import com.example.taskkeeper.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(networkConnection: NetworkConnection): Retrofit {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(networkConnection)
            .build()

        val retrofit by lazy {
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL_SPO)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

        return retrofit
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(networkConnection: NetworkConnection) =
        RetrofitInstance(provideRetrofitBuilder(networkConnection))

}