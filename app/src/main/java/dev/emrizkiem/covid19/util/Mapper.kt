package dev.emrizkiem.covid19.util

import dev.emrizkiem.covid19.data.model.global.DataResponse
import dev.emrizkiem.covid19.data.model.global.Location
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class Mapper {
    fun locationNameMapper(data: Array<Location>?) = data?.map {
        val name = it.provinceState ?: it.countryRegion
        it.copy().apply {
            countryRegion = name
        }
    }

    fun locationLastUpdateMapper(data: Array<Location>?) = data?.map {
        val newFormat = SimpleDateFormat("HH:mm - dd MM yyyy", Locale.getDefault())
        val newDate = Date(it.lastUpdate ?: 0L)

        it.copy().apply {
            readableLastUpdate = newFormat.format(newDate)
        }
    }

    fun lastUpdateMapper(data: DataResponse): DataResponse {
        val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        utcFormat.timeZone = TimeZone.getTimeZone("UTC")

        val date = utcFormat.parse(data.lastUpdate ?: "2020-03-07T15:03:06.000Z") as Date
        val newFormat = SimpleDateFormat("HH:mm - dd MMM, yyyy", Locale.getDefault())

        return data.copy().apply {
            lastUpdate = newFormat.format(date)
        }
    }
}