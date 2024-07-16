package com.example.numbersfactstesttask.presentation.di

import com.example.numbersfactstesttask.data.remote.NumbersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNumbersApi(): NumbersApi{
        return Retrofit.Builder()
            .baseUrl("http://numbersapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NumbersApi::class.java)
    }
}