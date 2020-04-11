package dev.emrizkiem.covid19.data.repository.home

import dev.emrizkiem.covid19.data.model.home.CovidOverviewResponse
import dev.emrizkiem.covid19.data.source.remote.ApiService
import retrofit2.Response

class HomeRepositoryImpl(private val apiService: ApiService): HomeRepository {
    override suspend fun getOverview(): Response<CovidOverviewResponse> {
        return apiService.overview()
    }
}