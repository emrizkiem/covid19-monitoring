package dev.emrizkiem.covid19.data.repository.global

import dev.emrizkiem.covid19.data.model.global.DataResponse
import dev.emrizkiem.covid19.data.model.global.Location
import retrofit2.Response

interface GlobalRepository {
    suspend fun loadOverviewGlobal(): Response<DataResponse>
    suspend fun loadLocationGlobal(): Response<List<Location>>

}