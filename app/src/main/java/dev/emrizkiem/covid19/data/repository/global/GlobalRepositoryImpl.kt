package dev.emrizkiem.covid19.data.repository.global

import com.google.gson.Gson
import dev.emrizkiem.covid19.data.model.global.DataResponse
import dev.emrizkiem.covid19.data.model.global.Location
import dev.emrizkiem.covid19.data.source.remote.ApiService
import dev.emrizkiem.covid19.data.source.remote.DataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONException
import retrofit2.Response

@ExperimentalCoroutinesApi
class GlobalRepositoryImpl(private val dataSource: DataSource) {
    fun loadOverview() = flow {
        try {
            val response =
                Gson().fromJson<DataResponse>(dataSource.loadMainData(), DataResponse::class.java)

            emit(response)
        } catch (e: JSONException) {
            emit(null)
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    fun loadLocation() = flow {
        try {
            val response: Array<Location> =
                Gson().fromJson(dataSource.loadDataWithLocation(), Array<Location>::class.java)

            emit(response)
        } catch (e: JSONException) {
            emit(null)
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)
}