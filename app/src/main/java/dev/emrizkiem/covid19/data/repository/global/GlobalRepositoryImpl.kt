package dev.emrizkiem.covid19.data.repository.global

import dev.emrizkiem.covid19.data.model.global.DataResponse
import dev.emrizkiem.covid19.data.model.global.Location
import dev.emrizkiem.covid19.data.source.remote.ApiService
import retrofit2.Response

class GlobalRepositoryImpl(private val apiService: ApiService) : GlobalRepository {
    override suspend fun loadOverviewGlobal(): Response<DataResponse> {
        return apiService.globalOverview()
    }

    override suspend fun loadLocationGlobal(): Response<List<Location>> {
        return apiService.location()
    }

}