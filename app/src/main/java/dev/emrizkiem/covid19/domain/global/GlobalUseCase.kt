package dev.emrizkiem.covid19.domain.global

import dev.emrizkiem.covid19.data.model.global.DataResponse
import dev.emrizkiem.covid19.data.model.global.Location
import dev.emrizkiem.covid19.data.repository.global.GlobalRepository
import dev.emrizkiem.covid19.util.ResultState
import dev.emrizkiem.covid19.util.fetchState

class GlobalUseCase(private val repository: GlobalRepository) {
    suspend fun getGlobalOverview(): ResultState<DataResponse> {
        return fetchState {
            val response = repository.loadOverviewGlobal()

            when (response.code()) {
                200 -> {
                    ResultState.Success(response.body())
                }
                else -> {
                    ResultState.Error(response.message())
                }
            }
        }
    }

    suspend fun getLocationGlobal(): ResultState<List<Location>> {
        return fetchState {
            val response = repository.loadLocationGlobal()
            when (response.code()) {
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