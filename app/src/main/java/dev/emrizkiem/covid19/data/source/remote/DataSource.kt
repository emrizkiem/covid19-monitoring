package dev.emrizkiem.covid19.data.source.remote

import java.net.URL

class DataSource {

    fun loadMainData()
            = URL("https://covid19.mathdro.id/api").readText()

    fun loadDataWithLocation()
            = URL("https://covid19.mathdro.id/api/confirmed").readText()
}