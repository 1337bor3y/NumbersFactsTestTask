package com.example.numbersfactstesttask.domain.use_case

import com.example.numbersfactstesttask.domain.model.NumberFact
import com.example.numbersfactstesttask.domain.model.Resource
import com.example.numbersfactstesttask.domain.repository.NumbersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFactsHistoryUseCase @Inject constructor(
    private val numbersRepository: NumbersRepository
) {
    operator fun invoke(): Flow<Resource<List<NumberFact>>> = flow {
        try {
            emit(Resource.Loading())
            val facts = numbersRepository.getFactsHistory()
            emit(Resource.Success(facts))
        } catch (e: Throwable) {
            emit(Resource.Error(e.localizedMessage
                ?: "An unexpected error occurred when downloading the history"))
        }
    }
}