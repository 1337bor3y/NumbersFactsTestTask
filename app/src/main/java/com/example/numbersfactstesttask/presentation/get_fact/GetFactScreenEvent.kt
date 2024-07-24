package com.example.numbersfactstesttask.presentation.get_fact

sealed interface GetFactScreenEvent {
    data class SetNumber(val number: String) : GetFactScreenEvent
    data class GetFact(val number: String) : GetFactScreenEvent
    data object GetRandomFact : GetFactScreenEvent
    data object GetFactHistory : GetFactScreenEvent
}