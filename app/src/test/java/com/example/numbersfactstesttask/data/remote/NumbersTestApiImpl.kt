package com.example.numbersfactstesttask.data.remote

import com.example.numbersfactstesttask.core.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object NumbersTestApiImpl {

    fun provideApi(): NumbersTestApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(NumbersTestApi::class.java)
    }
}