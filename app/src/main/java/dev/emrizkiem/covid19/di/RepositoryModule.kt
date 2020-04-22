package dev.emrizkiem.covid19.di

import dev.emrizkiem.covid19.data.repository.explore.ExploreRepository
import dev.emrizkiem.covid19.data.repository.explore.ExploreRepositoryImpl
import dev.emrizkiem.covid19.data.repository.global.GlobalRepository
import dev.emrizkiem.covid19.data.repository.global.GlobalRepositoryImpl
import dev.emrizkiem.covid19.data.repository.home.HomeRepository
import dev.emrizkiem.covid19.data.repository.home.HomeRepositoryImpl
import dev.emrizkiem.covid19.data.repository.info.InfoRepository
import dev.emrizkiem.covid19.data.repository.info.InfoRepositoryImpl
import dev.emrizkiem.covid19.util.Mapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
fun repositoryModule() = module {
    single<ExploreRepository> { ExploreRepositoryImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<InfoRepository> { InfoRepositoryImpl(get()) }
    single<GlobalRepository> { GlobalRepositoryImpl(get()) }
    single { Mapper() }
}