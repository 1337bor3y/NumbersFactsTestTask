package com.example.numbersfactstesttask.domain.repository

import com.example.numbersfactstesttask.domain.model.NumberFact

interface NumbersRepository {

    suspend fun getFact(number: String): NumberFact

    suspend fun getRandomFact(): NumberFact

    suspend fun getFactsHistory(): List<NumberFact>
}