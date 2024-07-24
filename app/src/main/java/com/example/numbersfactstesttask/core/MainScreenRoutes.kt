package com.example.numbersfactstesttask.core

import kotlinx.serialization.Serializable

@Serializable
sealed class MainScreenRoutes {

    @Serializable
    data object GetFactScreen : MainScreenRoutes()

    @Serializable
    data class ShowFullFactScreen(
        val number: String,
        val factText: String
    ) : MainScreenRoutes()
}