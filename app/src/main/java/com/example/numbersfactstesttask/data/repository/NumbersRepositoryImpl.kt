package com.example.numbersfactstesttask.data.repository

import com.example.numbersfactstesttask.data.local.NumberFactEntity
import com.example.numbersfactstesttask.data.local.NumbersDao
import com.example.numbersfactstesttask.data.remote.NumbersApi
import com.example.numbersfactstesttask.domain.model.NumberFact
import com.example.numbersfactstesttask.domain.repository.NumbersRepository
import javax.inject.Inject

class NumbersRepositoryImpl @Inject constructor(
    private val numbersApi: NumbersApi,
    private val numbersDao: NumbersDao
) : NumbersRepository {

    override suspend fun getFact(number: Int): NumberFact {
        try {
            val fact: String = numbersApi.getFact(number)
            numbersDao.upsertFact(
                NumberFactEntity(
                    number = number,
                    fact = fact
                )
            )
            return NumberFact(
                number = number,
                fact = fact
            )
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getRandomFact(): NumberFact {
        try {
            val fact = numbersApi.getRandomFact()
            val number = fact.substring(0, fact.indexOf(" ")).toInt()
            numbersDao.upsertFact(
                NumberFactEntity(
                    number = number,
                    fact = fact,
                ),
            )
            return NumberFact(
                number = number,
                fact = fact
            )
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getFactsHistory(): List<NumberFact> {
        return numbersDao.getAllFacts().map {
            NumberFact(
                number = it.number,
                fact = it.fact
            )
        }
    }
}