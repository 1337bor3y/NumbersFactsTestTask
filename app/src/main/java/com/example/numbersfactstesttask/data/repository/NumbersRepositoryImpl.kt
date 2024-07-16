package com.example.numbersfactstesttask.data.repository

import com.example.numbersfactstesttask.data.remote.NumbersApi
import com.example.numbersfactstesttask.domain.repository.NumbersRepository
import javax.inject.Inject

class NumbersRepositoryImpl @Inject constructor(
    private val numbersApi: NumbersApi
): NumbersRepository {

    override suspend fun getFact(number: Int): String {
        return numbersApi.getFact(number)
    }

    override suspend fun getRandomFact(): String {
        return numbersApi.getRandomFact()
    }

    override suspend fun getFactsHistory(): List<String> {
        TODO("Not yet implemented")
    }
}