package dev.emrizkiem.covid19.di

import dev.emrizkiem.covid19.data.source.remote.ApiConfig
import dev.emrizkiem.covid19.data.source.remote.DataSource
import org.koin.dsl.module

fun networkModule() = module {
    single { ApiConfig.createNetworkApi() }
    single { DataSource() }
}