package com.example.numbersfactstesttask.core.di

import android.content.Context
import androidx.room.Room
import com.example.numbersfactstesttask.core.util.Constants
import com.example.numbersfactstesttask.data.local.NumbersDao
import com.example.numbersfactstesttask.data.local.NumbersDatabase
import com.example.numbersfactstesttask.data.remote.NumbersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNumbersApi(): NumbersApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(NumbersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNumbersDatabase(@ApplicationContext context: Context): NumbersDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = NumbersDatabase::class.java,
            name = Constants.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNumbersDao(numbersDatabase: NumbersDatabase): NumbersDao {
        return numbersDatabase.numbersDao
    }
}