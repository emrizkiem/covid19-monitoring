package dev.emrizkiem.covid19.data.repository.info

import dev.emrizkiem.covid19.data.model.info.PreventionResponse
import dev.emrizkiem.covid19.data.model.info.SymptomsResponse
import retrofit2.Response

interface InfoRepository {
    suspend fun getSymptoms(): Response<SymptomsResponse>
    suspend fun getPrevention(): Response<PreventionResponse>
}