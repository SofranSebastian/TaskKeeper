package com.example.taskkeeper.dagger.modules

import com.example.taskkeeper.api.RetrofitInstance
import com.example.taskkeeper.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit {

        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

        return retrofit
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance() = RetrofitInstance(provideRetrofitBuilder())

}