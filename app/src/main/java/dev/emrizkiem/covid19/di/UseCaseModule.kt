package dev.emrizkiem.covid19.di

import dev.emrizkiem.covid19.usecase.explore.ExploreUseCase
import dev.emrizkiem.covid19.usecase.home.HomeUseCase
import dev.emrizkiem.covid19.usecase.info.InfoUseCase
import org.koin.dsl.module

fun useCaseModule() = module {
    factory { ExploreUseCase(get()) }
    factory { HomeUseCase(get()) }
    factory { InfoUseCase(get()) }
}