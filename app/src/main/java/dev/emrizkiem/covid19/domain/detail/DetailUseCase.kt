package dev.emrizkiem.covid19.domain.detail

import dev.emrizkiem.covid19.data.model.home.CovidDetail
import dev.emrizkiem.covid19.data.repository.detail.DetailRepository
import dev.emrizkiem.covid19.util.ResultState
import dev.emrizkiem.covid19.util.fetchState

class DetailUseCase(private val repository: DetailRepository) {
}