package dev.emrizkiem.covid19.data.repository.global

import dev.emrizkiem.covid19.data.model.global.DataResponse
import dev.emrizkiem.covid19.data.model.global.Location
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface GlobalRepository {
    suspend fun getOverviewGlobal(): Flow<Response<DataResponse>?>
    suspend fun getLocation(): Flow<Response<Location>?>
}