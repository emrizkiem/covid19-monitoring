package dev.emrizkiem.covid19.data.repository.home

import dev.emrizkiem.covid19.data.model.home.CovidOverview
import dev.emrizkiem.covid19.data.model.home.CovidOverviewResponse
import retrofit2.Response

interface HomeRepository {
    suspend fun getOverview(): Response<CovidOverview>
}