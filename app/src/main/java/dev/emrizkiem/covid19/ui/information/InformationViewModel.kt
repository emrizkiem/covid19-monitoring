package dev.emrizkiem.covid19.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emrizkiem.covid19.data.model.info.Prevention
import dev.emrizkiem.covid19.data.model.info.Symptoms
import dev.emrizkiem.covid19.domain.info.InfoUseCase
import dev.emrizkiem.covid19.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InformationViewModel(private val useCase: InfoUseCase) : ViewModel() {

    private val _symptoms = MutableLiveData<List<Symptoms>>()
    val symptoms: LiveData<List<Symptoms>> get() = _symptoms

    private val _prevention = MutableLiveData<List<Prevention>>()
    val prevention: LiveData<List<Prevention>> get() = _prevention

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getSymptoms()
        getPrevention()
    }

    private fun getPrevention() {
        _state.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                useCase.getPrevention()
            }

            when(response) {
                is ResultState.Success -> {
                    _prevention.postValue(response.data)
                }
                is ResultState.Error -> {
                    _error.postValue(response.error)
                }
            }
            _state.value = false
        }
    }

    fun getSymptoms() {
        _state.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                useCase.getSymptoms()
            }

            when(response) {
                is ResultState.Success -> {
                    _symptoms.postValue(response.data)
                }
                is ResultState.Error -> {
                    _error.postValue(response.error)
                }
            }
            _state.value = false
        }
    }
}