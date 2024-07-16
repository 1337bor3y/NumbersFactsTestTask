package com.example.numbersfactstesttask.domain.repository

interface NumbersRepository {

    suspend fun getFact(number: Int): String

    suspend fun getRandomFact(): String

    suspend fun getFactsHistory(): List<String>
}