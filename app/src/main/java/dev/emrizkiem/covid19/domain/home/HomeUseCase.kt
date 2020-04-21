package dev.emrizkiem.covid19.domain.home

import dev.emrizkiem.covid19.data.model.home.CovidDetail
import dev.emrizkiem.covid19.data.model.home.CovidOverview
import dev.emrizkiem.covid19.data.repository.home.HomeRepository
import dev.emrizkiem.covid19.util.ResultState
import dev.emrizkiem.covid19.util.fetchState

class HomeUseCase(private val repository: HomeRepository) {
    suspend fun getOverview(): ResultState<CovidOverview> {
        return fetchState {
            val response = repository.getOverview()

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

    suspend fun getDetail(): ResultState<List<CovidDetail>> {
        return fetchState {
            val response = repository.getDetail()

            when(response.code()) {
                200 -> {
                    ResultState.Success(response.body()?.data)
                }
                else -> {
                    ResultState.Error(response.message())
                }
            }
        }
    }
}