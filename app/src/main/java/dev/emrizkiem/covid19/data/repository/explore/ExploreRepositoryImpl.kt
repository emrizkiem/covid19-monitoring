package dev.emrizkiem.covid19.data.repository.explore

import dev.emrizkiem.covid19.BuildConfig
import dev.emrizkiem.covid19.data.model.explore.ExploreResponse
import dev.emrizkiem.covid19.data.source.remote.ApiService
import retrofit2.Response

class ExploreRepositoryImpl(private val apiService: ApiService): ExploreRepository {
    override suspend fun getExplore(): Response<ExploreResponse> {
        return apiService.explore("COVID", BuildConfig.API_KEY, "id")
    }
}