package com.example.numbersfactstesttask.presentation.get_fact

import com.example.numbersfactstesttask.domain.model.NumberFact
import com.example.numbersfactstesttask.domain.model.Resource
import com.example.numbersfactstesttask.domain.use_case.GetFactUseCase
import com.example.numbersfactstesttask.domain.use_case.GetFactsHistoryUseCase
import com.example.numbersfactstesttask.domain.use_case.GetRandomFactUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetFactViewModelTest {

    private val getFactUseCase = mock<GetFactUseCase>()
    private val getRandomFactUseCase = mock<GetRandomFactUseCase>()
    private val getFactsHistoryUseCase = mock<GetFactsHistoryUseCase>()
    private lateinit var getFactViewModel: GetFactViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @Before
    fun setUp() {
        Mockito.`when`(getFactsHistoryUseCase()).thenReturn(
            flow {
                emit(Resource.Loading())
                emit(Resource.Success(arrayListOf()))
            }
        )
        getFactViewModel = GetFactViewModel(
            getFactUseCase = getFactUseCase,
            getRandomFactUseCase = getRandomFactUseCase,
            getFactsHistoryUseCase = getFactsHistoryUseCase
        )
    }

    @After
    fun tearDown() {
        Mockito.reset(getFactUseCase, getRandomFactUseCase, getFactsHistoryUseCase)
    }

    @Test
    fun `Get fact with number, result expected fact at 0 index`() = runTest {
        val expected = NumberFact("9", "Fact")
        Mockito.`when`(getFactUseCase("9")).thenReturn(
            flow {
                emit(Resource.Loading())
                emit(Resource.Success(expected))
            }
        )

        getFactViewModel.onEvent(GetFactScreenEvent.SetNumber("9"))
        getFactViewModel.onEvent(GetFactScreenEvent.GetFact("9"))
        val actual = getFactViewModel.state.value

        assertEquals(expected, actual.facts[0])
    }

    @Test
    fun `Get fact with number, result loading false`() = runTest {
        val expected = NumberFact("9", "Fact")
        Mockito.`when`(getFactUseCase("9")).thenReturn(
            flow {
                emit(Resource.Loading())
                emit(Resource.Success(expected))
            }
        )

        getFactViewModel.onEvent(GetFactScreenEvent.SetNumber("9"))
        getFactViewModel.onEvent(GetFactScreenEvent.GetFact("9"))
        val actual = getFactViewModel.state.value

        assertEquals(false, actual.isLoading)
    }

    @Test
    fun `Get fact with number, result error is empty`() = runTest {
        val expected = NumberFact("9", "Fact")
        Mockito.`when`(getFactUseCase("9")).thenReturn(
            flow {
                emit(Resource.Loading())
                emit(Resource.Success(expected))
            }
        )

        getFactViewModel.onEvent(GetFactScreenEvent.SetNumber("9"))
        getFactViewModel.onEvent(GetFactScreenEvent.GetFact("9"))
        val actual = getFactViewModel.state.value

        assert(actual.error.isEmpty())
    }

    @Test
    fun `Get fact with number with exception, result error`() = runTest {
        Mockito.`when`(getFactUseCase("9")).thenReturn(
            flow {
                emit(Resource.Error("Error"))
            }
        )

        getFactViewModel.onEvent(GetFactScreenEvent.SetNumber("9"))
        getFactViewModel.onEvent(GetFactScreenEvent.GetFact("9"))
        val actual = getFactViewModel.state.value

        assert(actual.error.isNotEmpty())
    }

    @Test
    fun `Get random fact, result expected fact at 0 index`() = runTest {
        val expected = NumberFact("9", "Fact")
        Mockito.`when`(getRandomFactUseCase()).thenReturn(
            flow {
                emit(Resource.Loading())
                emit(Resource.Success(expected))
            }
        )

        getFactViewModel.onEvent(GetFactScreenEvent.GetRandomFact)
        val actual = getFactViewModel.state.value

        assertEquals(expected, actual.facts[0])
    }

    @Test
    fun `Get random fact, result loading false`() = runTest {
        val expected = NumberFact("9", "Fact")
        Mockito.`when`(getRandomFactUseCase()).thenReturn(
            flow {
                emit(Resource.Loading())
                emit(Resource.Success(expected))
            }
        )

        getFactViewModel.onEvent(GetFactScreenEvent.GetRandomFact)
        val actual = getFactViewModel.state.value

        assertEquals(false, actual.isLoading)
    }

    @Test
    fun `Get random fact, result error is empty`() = runTest {
        val expected = NumberFact("9", "Fact")
        Mockito.`when`(getRandomFactUseCase()).thenReturn(
            flow {
                emit(Resource.Loading())
                emit(Resource.Success(expected))
            }
        )

        getFactViewModel.onEvent(GetFactScreenEvent.GetRandomFact)
        val actual = getFactViewModel.state.value

        assert(actual.error.isEmpty())
    }

    @Test
    fun `Get random fact with exception, result error`() = runTest {
        Mockito.`when`(getRandomFactUseCase()).thenReturn(
            flow {
                emit(Resource.Error("Error"))
            }
        )

        getFactViewModel.onEvent(GetFactScreenEvent.GetRandomFact)
        val actual = getFactViewModel.state.value

        assert(actual.error.isNotEmpty())
    }

    @Test
    fun `Get fact history, result expected fact at 0 index`() = runTest {
        val expected = NumberFact("9", "Fact")
        Mockito.`when`(getFactsHistoryUseCase()).thenReturn(
            flow {
                emit(Resource.Loading())
                emit(Resource.Success(arrayListOf(expected)))
            }
        )

        getFactViewModel.onEvent(GetFactScreenEvent.GetFactHistory)
        val actual = getFactViewModel.state.value

        assertEquals(expected, actual.facts[0])
    }

    @Test
    fun `Get fact history, result loading false`() = runTest {
        val expected = NumberFact("9", "Fact")
        Mockito.`when`(getFactsHistoryUseCase()).thenReturn(
            flow {
                emit(Resource.Loading())
                emit(Resource.Success(arrayListOf(expected)))
            }
        )

        getFactViewModel.onEvent(GetFactScreenEvent.GetFactHistory)
        val actual = getFactViewModel.state.value

        assertEquals(false, actual.isLoading)
    }

    @Test
    fun `Get fact history, result error is empty`() = runTest {
        val expected = NumberFact("9", "Fact")
        Mockito.`when`(getFactsHistoryUseCase()).thenReturn(
            flow {
                emit(Resource.Loading())
                emit(Resource.Success(arrayListOf(expected)))
            }
        )

        getFactViewModel.onEvent(GetFactScreenEvent.GetFactHistory)
        val actual = getFactViewModel.state.value

        assert(actual.error.isEmpty())
    }

    @Test
    fun `Get fact history with exception, result error`() = runTest {
        Mockito.`when`(getFactsHistoryUseCase()).thenReturn(
            flow {
                emit(Resource.Error("Error"))
            }
        )

        getFactViewModel.onEvent(GetFactScreenEvent.GetFactHistory)
        val actual = getFactViewModel.state.value

        assert(actual.error.isNotEmpty())
    }
}