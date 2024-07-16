package com.example.numbersfactstesttask.domain.repository

import com.example.numbersfactstesttask.domain.model.NumberFact

interface NumbersRepository {

    suspend fun getFact(number: Int): NumberFact

    suspend fun getRandomFact(): NumberFact

    fun getFactsHistory(): List<NumberFact>
}