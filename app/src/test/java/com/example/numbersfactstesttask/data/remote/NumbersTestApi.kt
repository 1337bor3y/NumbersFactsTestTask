package com.example.numbersfactstesttask.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersTestApi {

    @GET("/{number}")
    suspend fun getFact(@Path("number") number: String): String

    @GET("/random/math")
    suspend fun getRandomFact(): String
}