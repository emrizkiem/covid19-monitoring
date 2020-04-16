package dev.emrizkiem.covid19.domain.explore

import dev.emrizkiem.covid19.data.model.explore.ArticlesItem
import dev.emrizkiem.covid19.data.repository.explore.ExploreRepository
import dev.emrizkiem.covid19.util.ResultState
import dev.emrizkiem.covid19.util.fetchState

class ExploreUseCase(private val repository: ExploreRepository) {
    suspend fun getExplore(): ResultState<List<ArticlesItem>> {
        return fetchState {
            val response = repository.getExplore()

            when(response.code()) {
                200 -> {
                    ResultState.Success(response.body()?.articles)
                }
                else -> {
                    ResultState.Error(response.message())
                }
            }
        }
    }
}