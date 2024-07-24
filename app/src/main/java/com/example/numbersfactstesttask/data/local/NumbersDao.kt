package com.example.numbersfactstesttask.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface NumbersDao {

    @Upsert
    suspend fun upsertFact(fact: NumberFactEntity)

    @Query("SELECT * FROM numberfactentity")
    suspend fun getAllFacts(): List<NumberFactEntity>
}