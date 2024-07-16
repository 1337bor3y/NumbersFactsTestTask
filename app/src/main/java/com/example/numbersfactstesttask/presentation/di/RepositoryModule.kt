package com.example.numbersfactstesttask.presentation.di

import com.example.numbersfactstesttask.data.repository.NumbersRepositoryImpl
import com.example.numbersfactstesttask.domain.repository.NumbersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideNumbersRepository(
        numbersRepositoryImpl: NumbersRepositoryImpl
    ): NumbersRepository
}