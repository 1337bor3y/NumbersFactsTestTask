package com.example.numbersfactstesttask.domain.use_case

import com.example.numbersfactstesttask.domain.model.NumberFact
import com.example.numbersfactstesttask.domain.model.Resource
import com.example.numbersfactstesttask.domain.repository.NumbersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetFactUseCase @Inject constructor(
    private val numbersRepository: NumbersRepository
) {
    operator fun invoke(number: String): Flow<Resource<NumberFact>> = flow {
        try {
            emit(Resource.Loading())
            val fact = numbersRepository.getFact(number)
            emit(Resource.Success(fact))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage
                ?: "An unexpected error occurred when getting a fact"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}