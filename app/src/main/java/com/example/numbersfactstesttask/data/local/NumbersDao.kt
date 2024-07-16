package com.example.numbersfactstesttask.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.numbersfactstesttask.domain.model.NumberFact

@Dao
interface NumbersDao {

    @Upsert
    suspend fun upsertFact(fact: NumberFactEntity)

    @Query("SELECT * FROM numberfactentity")
    fun getAllFacts(): List<NumberFact>
}