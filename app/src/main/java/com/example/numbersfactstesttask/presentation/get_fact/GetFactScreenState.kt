package com.example.numbersfactstesttask.presentation.get_fact

import com.example.numbersfactstesttask.domain.model.NumberFact

data class GetFactScreenState(
    val isLoading: Boolean = false,
    val number: String = "",
    val facts: ArrayList<NumberFact> = arrayListOf(),
    val error: String = ""
)