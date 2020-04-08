package dev.emrizkiem.covid19.data.source.remote

import dev.emrizkiem.covid19.data.model.explore.ExploreResponse
import dev.emrizkiem.covid19.data.model.home.CovidDaily
import dev.emrizkiem.covid19.data.model.home.CovidDetail
import dev.emrizkiem.covid19.data.model.home.CovidOverview
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api")
    fun overview(): Call<CovidOverview>

    @GET("api/daily")
    fun daily(): Call<CovidDaily>

    @GET("api/confirmed")
    fun confirmed(): Call<CovidDetail>

    @GET("api/deaths")
    fun deaths(): Call<CovidDetail>

    @GET("api/recovered")
    fun recovered(): Call<CovidDetail>

    @GET("https://newsapi.org/v2/top-headlines")
    suspend fun explore(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?,
        @Query("country") country: String?
    ): Response<ExploreResponse>
}