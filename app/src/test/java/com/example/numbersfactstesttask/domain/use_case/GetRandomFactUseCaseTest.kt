package com.example.numbersfactstesttask.domain.use_case

import app.cash.turbine.test
import com.example.numbersfactstesttask.domain.model.NumberFact
import com.example.numbersfactstesttask.domain.repository.NumbersRepository
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class GetRandomFactUseCaseTest {

    private val numbersRepository = mock<NumbersRepository>()

    @After
    fun tearDown() {
        Mockito.reset(numbersRepository)
    }

    @Test
    fun `Invoke, result NumberFact`() = runBlocking {

        val expected = NumberFact("9", "Fact")
        Mockito.`when`(numbersRepository.getRandomFact()).thenReturn(expected)

        val gerRandomFactUseCase = GetRandomFactUseCase(numbersRepository = numbersRepository)
        val actualFlow = gerRandomFactUseCase.invoke()

        actualFlow.test {
            awaitItem()
            val actual = awaitItem().data
            assertEquals(expected, actual)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Invoke with HttpException, result exception handled with message`() = runBlocking {

        val httpException = HttpException(
            Response.error<Any>(
                404,
                "Error".toResponseBody()
            )
        )
        Mockito.`when`(numbersRepository.getRandomFact()).thenThrow(httpException)

        val getRandomFactUseCase = GetRandomFactUseCase(numbersRepository = numbersRepository)
        val actualFlow = getRandomFactUseCase.invoke()
        val expected = httpException.localizedMessage
            ?: "An unexpected error occurred when getting a random fact"

        actualFlow.test {
            awaitItem()
            val actual = awaitItem().message
            assertEquals(expected, actual)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Invoke with IOException, result exception handled with message`() = runBlocking {

        Mockito.`when`(numbersRepository.getRandomFact()).thenAnswer {
            throw IOException()
        }

        val getRandomFactUseCase = GetRandomFactUseCase(numbersRepository = numbersRepository)
        val actualFlow = getRandomFactUseCase.invoke()
        val expected = "Couldn't reach server. Check your internet connection"

        actualFlow.test {
            awaitItem()
            val actual = awaitItem().message
            assertEquals(expected, actual)
            cancelAndIgnoreRemainingEvents()
        }
    }
}