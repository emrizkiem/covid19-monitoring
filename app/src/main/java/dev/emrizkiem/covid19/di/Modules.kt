package dev.emrizkiem.covid19.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

@ExperimentalCoroutinesApi
class Modules {
    private val modules: List<Module> = listOf(
        networkModule(),
        repositoryModule(),
        useCaseModule(),
        viewModelModule()
    )

    init {
        loadKoinModules(modules)
    }
}