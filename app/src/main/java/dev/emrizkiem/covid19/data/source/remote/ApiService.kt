package dev.emrizkiem.covid19.data.source.remote

import dev.emrizkiem.covid19.data.model.explore.ExploreResponse
import dev.emrizkiem.covid19.data.model.home.CovidDetail
import dev.emrizkiem.covid19.data.model.home.CovidOverview
import dev.emrizkiem.covid19.data.model.home.CovidOverviewResponse
import dev.emrizkiem.covid19.data.model.info.PreventionResponse
import dev.emrizkiem.covid19.data.model.info.SymptomsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api")
    suspend fun overview(): Response<CovidOverviewResponse>

//    @GET("api/confirmed")
//    suspend fun confirmed(): Response<CovidOverview>
//
//    @GET("api/deaths")
//    suspend fun deaths(): Response<CovidDetail>
//
//    @GET("api/recovered")
//    suspend fun recovered(): Response<CovidDetail>

    @GET("https://newsapi.org/v2/top-headlines")
    suspend fun explore(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?,
        @Query("country") country: String?
    ): Response<ExploreResponse>

    @GET("http://www.mocky.io/v2/5e9256983100002a00462c5f")
    suspend fun symptoms(): Response<SymptomsResponse>

    @GET("http://www.mocky.io/v2/5e95e10b2f000068780255df")
    suspend fun prevention(): Response<PreventionResponse>
}