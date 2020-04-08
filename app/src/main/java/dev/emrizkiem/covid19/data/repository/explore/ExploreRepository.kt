package dev.emrizkiem.covid19.data.repository.explore

import dev.emrizkiem.covid19.data.model.explore.ExploreResponse
import retrofit2.Response

interface ExploreRepository {
    suspend fun getExplore(): Response<ExploreResponse>
}