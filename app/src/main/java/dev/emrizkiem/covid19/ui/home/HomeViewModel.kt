package dev.emrizkiem.covid19.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emrizkiem.covid19.data.model.home.CovidOverview
import dev.emrizkiem.covid19.domain.home.HomeUseCase
import dev.emrizkiem.covid19.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {

    private val _overview = MutableLiveData<CovidOverview>()
    val overview: LiveData<CovidOverview> get() = _overview

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getOverview()
    }

    private fun getOverview() {
        _state.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                useCase.getOverview()
            }

            when(response) {
                is ResultState.Success -> {
                    _overview.postValue(response.data)
                }
                is ResultState.Error -> {
                    _error.postValue(response.error)
                }
            }
            _state.value = false
        }
    }

}