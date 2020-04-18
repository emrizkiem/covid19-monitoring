package dev.emrizkiem.covid19.di

import dev.emrizkiem.covid19.ui.detail.DetailViewModel
import dev.emrizkiem.covid19.ui.explore.ExploreViewModel
import dev.emrizkiem.covid19.ui.home.HomeViewModel
import dev.emrizkiem.covid19.ui.information.InformationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModel { ExploreViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { InformationViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}