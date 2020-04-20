package dev.emrizkiem.covid19.data.repository.home

import dev.emrizkiem.covid19.data.model.home.CovidDetailResponse
import dev.emrizkiem.covid19.data.model.home.CovidOverview
import dev.emrizkiem.covid19.data.source.remote.ApiService
import retrofit2.Response

class HomeRepositoryImpl(private val apiService: ApiService): HomeRepository {
    override suspend fun getOverview(): Response<CovidOverview> {
        return apiService.overview()
    }

    override suspend fun getDetail(): Response<CovidDetailResponse> {
        return apiService.detail()
    }

}