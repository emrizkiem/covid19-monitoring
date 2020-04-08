package dev.emrizkiem.covid19.di

import dev.emrizkiem.covid19.ui.explore.ExploreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModel { ExploreViewModel(get()) }
}