package dev.emrizkiem.covid19.di

import dev.emrizkiem.covid19.data.repository.explore.ExploreRepository
import dev.emrizkiem.covid19.data.repository.explore.ExploreRepositoryImpl
import org.koin.dsl.module

fun repositoryModule() = module {
    single<ExploreRepository> { ExploreRepositoryImpl(get()) }
}