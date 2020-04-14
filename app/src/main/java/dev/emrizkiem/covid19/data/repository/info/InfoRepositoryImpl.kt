package dev.emrizkiem.covid19.data.repository.info

import dev.emrizkiem.covid19.data.model.info.PreventionResponse
import dev.emrizkiem.covid19.data.model.info.SymptomsResponse
import dev.emrizkiem.covid19.data.source.remote.ApiService
import retrofit2.Response

class InfoRepositoryImpl(private val apiService: ApiService): InfoRepository {
    override suspend fun getSymptoms(): Response<SymptomsResponse> {
        return apiService.symptoms()
    }

    override suspend fun getPrevention(): Response<PreventionResponse> {
        return apiService.prevention()
    }
}