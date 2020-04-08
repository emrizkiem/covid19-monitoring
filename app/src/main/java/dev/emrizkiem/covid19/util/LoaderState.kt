package dev.emrizkiem.covid19.util

sealed class LoaderState {
    object ShowLoading: LoaderState()
    object HideLoading: LoaderState()
}