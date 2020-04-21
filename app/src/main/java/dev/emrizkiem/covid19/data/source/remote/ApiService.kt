package dev.emrizkiem.covid19.data.source.remote

import dev.emrizkiem.covid19.data.model.explore.ArticlesResponse
import dev.emrizkiem.covid19.data.model.global.DataResponse
import dev.emrizkiem.covid19.data.model.home.CovidDetailResponse
import dev.emrizkiem.covid19.data.model.home.CovidOverview
import dev.emrizkiem.covid19.data.model.info.PreventionResponse
import dev.emrizkiem.covid19.data.model.info.SymptomsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("https://indonesia-covid-19.mathdro.id/api/")
    suspend fun overview(): Response<CovidOverview>

    @GET("https://indonesia-covid-19.mathdro.id/api/provinsi")
    suspend fun detail(): Response<CovidDetailResponse>

    @GET("api/")
    suspend fun globalOverview(): Response<DataResponse>

    @GET("https://newsapi.org/v2/top-headlines")
    suspend fun explore(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?,
        @Query("country") country: String?
    ): Response<ArticlesResponse>

    @GET("http://www.mocky.io/v2/5e9256983100002a00462c5f")
    suspend fun symptoms(): Response<SymptomsResponse>

    @GET("http://www.mocky.io/v2/5e95e10b2f000068780255df")
    suspend fun prevention(): Response<PreventionResponse>
}