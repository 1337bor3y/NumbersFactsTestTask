package com.example.numbersfactstesttask.presentation.get_fact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbersfactstesttask.domain.model.NumberFact
import com.example.numbersfactstesttask.domain.model.Resource
import com.example.numbersfactstesttask.domain.use_case.GetFactUseCase
import com.example.numbersfactstesttask.domain.use_case.GetFactsHistoryUseCase
import com.example.numbersfactstesttask.domain.use_case.GetRandomFactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GetFactViewModel @Inject constructor(
    private val getFactUseCase: GetFactUseCase,
    private val getRandomFactUseCase: GetRandomFactUseCase,
    private val getFactsHistoryUseCase: GetFactsHistoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(GetFactScreenState())
    val state = _state.asStateFlow()

    init {
        getFactHistory()
    }

    fun onEvent(event: GetFactScreenEvent) {
        when (event) {
            is GetFactScreenEvent.GetFact -> getFact(state.value.number)
            GetFactScreenEvent.GetRandomFact -> getRandomFact()
            is GetFactScreenEvent.SetNumber -> _state.update {
                it.copy(
                    number = event.number
                )
            }
            GetFactScreenEvent.GetFactHistory -> getFactHistory()
        }
    }

    private fun getFact(number: String) {
        getFactUseCase(number).onEach { result ->
            resultFlow(result) {
                _state.update {
                    it.facts.add(result.data!!)
                    it.copy(
                        error = "",
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getRandomFact() {
        getRandomFactUseCase().onEach { result ->
            resultFlow(result) {
                _state.update {
                    it.facts.add(result.data!!)
                    it.copy(
                        error = "",
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getFactHistory() {
        getFactsHistoryUseCase().onEach { result ->
            resultFlow(result) {
                _state.update {
                    it.copy(
                        facts = (result.data ?: arrayListOf()) as ArrayList<NumberFact>,
                        error = "",
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun <T> resultFlow(
        result: Resource<T>,
        successFunction: () -> Unit
    ) {
        when (result) {
            is Resource.Error -> _state.update {
                it.copy(
                    error = result.message ?: "An unexpected error occurred",
                    isLoading = false
                )
            }

            is Resource.Loading -> _state.update {
                it.copy(
                    error = "",
                    isLoading = true
                )
            }

            is Resource.Success -> successFunction()
        }
    }
}