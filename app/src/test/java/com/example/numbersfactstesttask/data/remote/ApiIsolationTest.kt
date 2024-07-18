package com.example.numbersfactstesttask.data.remote

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test

class ApiIsolationTest {

    @Test
    fun `Get fact, result not null`() {
        val api = NumbersTestApiImpl.provideApi()
        val test = runBlocking {
            api.getFact("9")
        }

        assertNotNull(test)
    }

    @Test
    fun `Get random fact, result not null`() {
        val api = NumbersTestApiImpl.provideApi()
        val test = runBlocking {
            api.getRandomFact()
        }

        assertNotNull(test)
    }

    @Test
    fun `Get random fact, first word is number`() {
        val api = NumbersTestApiImpl.provideApi()
        val test = runBlocking {
            api.getRandomFact()
        }
        val number = test.substring(0, test.indexOf(" ")).toIntOrNull()

        assertNotNull(number)
    }
}