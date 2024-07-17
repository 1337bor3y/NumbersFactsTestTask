package com.example.numbersfactstesttask.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NumberFactEntity(
    @PrimaryKey
    val number: Int,
    val fact: String
)