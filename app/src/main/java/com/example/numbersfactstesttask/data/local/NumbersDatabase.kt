package com.example.numbersfactstesttask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NumberFactEntity::class],
    version = 1
)
abstract class NumbersDatabase: RoomDatabase() {

    abstract val numbersDao: NumbersDao
}