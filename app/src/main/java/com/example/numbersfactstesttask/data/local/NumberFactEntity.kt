package com.example.numbersfactstesttask.data.local

import androidx.room.Entity

@Entity
data class NumberFactEntity(
    val number: Int,
    val fact: String
)