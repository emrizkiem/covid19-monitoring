package dev.emrizkiem.covid19.di

import dev.emrizkiem.covid19.data.repository.detail.DetailRepository
import dev.emrizkiem.covid19.data.repository.detail.DetailRepositoryImpl
import dev.emrizkiem.covid19.data.repository.explore.ExploreRepository
import dev.emrizkiem.covid19.data.repository.explore.ExploreRepositoryImpl
import dev.emrizkiem.covid19.data.repository.home.HomeRepository
import dev.emrizkiem.covid19.data.repository.home.HomeRepositoryImpl
import dev.emrizkiem.covid19.data.repository.info.InfoRepository
import dev.emrizkiem.covid19.data.repository.info.InfoRepositoryImpl
import org.koin.dsl.module

fun repositoryModule() = module {
    single<ExploreRepository> { ExploreRepositoryImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<InfoRepository> { InfoRepositoryImpl(get()) }
    single<DetailRepository> { DetailRepositoryImpl(get()) }
}