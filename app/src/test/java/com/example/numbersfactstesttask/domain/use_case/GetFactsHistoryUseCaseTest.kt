package com.example.numbersfactstesttask.domain.use_case

import app.cash.turbine.test
import com.example.numbersfactstesttask.domain.model.NumberFact
import com.example.numbersfactstesttask.domain.repository.NumbersRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetFactsHistoryUseCaseTest {

    private val numbersRepository = mock<NumbersRepository>()

    @After
    fun tearDown() {
        Mockito.reset(numbersRepository)
    }

    @Test
    fun `Invoke, result list of NumberFact`() = runBlocking {

        val expected = listOf(
            NumberFact("9", "Fact9"), NumberFact(
                "99",
                "Fact99"
            )
        )
        Mockito.`when`(numbersRepository.getFactsHistory()).thenReturn(expected)

        val getFactsHistoryUseCase = GetFactsHistoryUseCase(numbersRepository = numbersRepository)
        val actualFlow = getFactsHistoryUseCase.invoke()

        actualFlow.test {
            awaitItem()
            val actual = awaitItem().data
            assertEquals(expected, actual)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Invoke with Exception, result exception handled with message`() = runBlocking {

        val exception = Exception()
        Mockito.`when`(numbersRepository.getFactsHistory()).thenAnswer {
            throw exception
        }

        val getFactsHistoryUseCase = GetFactsHistoryUseCase(numbersRepository = numbersRepository)
        val actualFlow = getFactsHistoryUseCase.invoke()
        val expected = exception.localizedMessage
            ?: "An unexpected error occurred when downloading the history"

        actualFlow.test {
            awaitItem()
            val actual = awaitItem().message
            assertEquals(expected, actual)
            cancelAndIgnoreRemainingEvents()
        }
    }
}