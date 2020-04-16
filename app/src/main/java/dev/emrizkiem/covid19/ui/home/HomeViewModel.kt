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

    private val _confirmed = MutableLiveData<CovidOverview>()
    val confirmed: LiveData<CovidOverview> get() = _confirmed

    private val _recovered = MutableLiveData<CovidOverview>()
    val recovered: LiveData<CovidOverview> get() = _recovered

    private val _death = MutableLiveData<CovidOverview>()
    val death: LiveData<CovidOverview> get() = _death

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getConfirmed()
        getRecovered()
        getDeath()
    }

    private fun getDeath() {
        _state.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                useCase.getDeath()
            }

            when(response) {
                is ResultState.Success -> {
                    _death.postValue(response.data)
                }
                is ResultState.Error -> {
                    _error.postValue(response.error)
                }
            }
            _state.value = false
        }
    }

    private fun getRecovered() {
        _state.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                useCase.getRecovered()
            }

            when(response) {
                is ResultState.Success -> {
                    _recovered.postValue(response.data)
                }
                is ResultState.Error -> {
                    _error.postValue(response.error)
                }
            }
            _state.value = false
        }
    }

    private fun getConfirmed() {
        _state.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                useCase.getConfirmed()
            }

            when(response) {
                is ResultState.Success -> {
                    _confirmed.postValue(response.data)
                }
                is ResultState.Error -> {
                    _error.postValue(response.error)
                }
            }
            _state.value = false
        }
    }
}