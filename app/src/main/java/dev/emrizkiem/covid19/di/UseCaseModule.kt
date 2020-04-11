package dev.emrizkiem.covid19.di

import dev.emrizkiem.covid19.usecase.explore.ExploreUseCase
import dev.emrizkiem.covid19.usecase.home.HomeUseCase
import org.koin.dsl.module

fun useCaseModule() = module {
    factory { ExploreUseCase(get()) }
    factory { HomeUseCase(get()) }
}