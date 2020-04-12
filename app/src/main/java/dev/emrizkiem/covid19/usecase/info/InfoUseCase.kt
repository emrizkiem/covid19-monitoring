package dev.emrizkiem.covid19.usecase.info

import dev.emrizkiem.covid19.data.model.info.Symptoms
import dev.emrizkiem.covid19.data.repository.info.InfoRepository
import dev.emrizkiem.covid19.util.ResultState
import dev.emrizkiem.covid19.util.fetchState

class InfoUseCase(private val repository: InfoRepository) {
    suspend fun getSymptoms(): ResultState<List<Symptoms>> {
        return fetchState {
            val response = repository.getSymptoms()

            when(response.code()) {
                200 -> {
                    ResultState.Success(response.body()?.symptoms)
                }
                else -> {
                    ResultState.Error(response.message())
                }
            }
        }
    }
}