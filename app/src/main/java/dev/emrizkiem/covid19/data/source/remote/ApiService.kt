package dev.emrizkiem.covid19.data.source.remote

import dev.emrizkiem.covid19.data.model.explore.ExploreResponse
import dev.emrizkiem.covid19.data.model.home.CovidDetail
import dev.emrizkiem.covid19.data.model.home.CovidOverviewResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api")
    suspend fun overview(): Response<CovidOverviewResponse>

    @GET("api/confirmed")
    suspend fun confirmed(): Call<CovidDetail>

    @GET("api/deaths")
    suspend fun deaths(): Call<CovidDetail>

    @GET("api/recovered")
    suspend fun recovered(): Call<CovidDetail>

    @GET("https://newsapi.org/v2/top-headlines")
    suspend fun explore(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?,
        @Query("country") country: String?
    ): Response<ExploreResponse>
}