package dev.emrizkiem.covid19.di

import dev.emrizkiem.covid19.domain.explore.ExploreUseCase
import dev.emrizkiem.covid19.domain.global.GlobalUseCase
import dev.emrizkiem.covid19.domain.home.HomeUseCase
import dev.emrizkiem.covid19.domain.info.InfoUseCase
import org.koin.dsl.module

fun useCaseModule() = module {
    factory { ExploreUseCase(get()) }
    factory { HomeUseCase(get()) }
    factory { InfoUseCase(get()) }
    factory { GlobalUseCase(get()) }
}