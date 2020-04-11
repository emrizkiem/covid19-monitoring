package dev.emrizkiem.covid19.usecase.home

import dev.emrizkiem.covid19.data.model.home.CovidOverviewItem
import dev.emrizkiem.covid19.data.repository.home.HomeRepository
import dev.emrizkiem.covid19.util.ResultState
import dev.emrizkiem.covid19.util.fetchState

class HomeUseCase(private val repository: HomeRepository) {
    suspend fun getOverview(): ResultState<CovidOverviewItem> {
        return fetchState {
            val response = repository.getOverview()

            when(response.code()) {
                200 -> {
                    ResultState.Success(response.body()?.confirmed)
                }
                else -> {
                    ResultState.Error(response.message())
                }
            }
        }
    }
}