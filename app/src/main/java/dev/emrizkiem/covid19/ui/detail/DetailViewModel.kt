package dev.emrizkiem.covid19.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emrizkiem.covid19.data.model.home.CovidDetail
import dev.emrizkiem.covid19.data.repository.detail.DetailRepository
import dev.emrizkiem.covid19.domain.detail.DetailUseCase
import dev.emrizkiem.covid19.util.CaseType
import dev.emrizkiem.covid19.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val useCase: DetailUseCase): ViewModel() {


}