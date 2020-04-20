package dev.emrizkiem.covid19.data.repository.home

import dev.emrizkiem.covid19.data.model.home.CovidDetailResponse
import dev.emrizkiem.covid19.data.model.home.CovidOverview
import retrofit2.Response

interface HomeRepository {
    suspend fun getOverview(): Response<CovidOverview>
    suspend fun getDetail(): Response<CovidDetailResponse>
}