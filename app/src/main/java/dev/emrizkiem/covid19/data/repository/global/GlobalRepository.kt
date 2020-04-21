package dev.emrizkiem.covid19.data.repository.global

import dev.emrizkiem.covid19.data.model.global.DataResponse
import retrofit2.Response

interface GlobalRepository {
    suspend fun getOverviewGlobal(): Response<DataResponse>
}