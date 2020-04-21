package dev.emrizkiem.covid19.domain.global

import dev.emrizkiem.covid19.data.model.global.Data
import dev.emrizkiem.covid19.data.model.global.DataResponse
import dev.emrizkiem.covid19.data.repository.global.GlobalRepository
import dev.emrizkiem.covid19.util.ResultState
import dev.emrizkiem.covid19.util.fetchState

class GlobalUseCase(private val repository: GlobalRepository) {
    suspend fun getOverviewGlobal(): ResultState<DataResponse> {
        return fetchState {
            val response = repository.getOverviewGlobal()

            when(response.code()) {
                200 -> {
                    ResultState.Success(response.body())
                }
                else -> {
                    ResultState.Error(response.message())
                }
            }
        }
    }
}