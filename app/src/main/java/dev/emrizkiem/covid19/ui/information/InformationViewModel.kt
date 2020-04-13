package dev.emrizkiem.covid19.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emrizkiem.covid19.data.model.info.Symptoms
import dev.emrizkiem.covid19.usecase.info.InfoUseCase
import dev.emrizkiem.covid19.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InformationViewModel(private val useCase: InfoUseCase) : ViewModel() {

    private val _symptoms = MutableLiveData<List<Symptoms>>()
    val symptoms: LiveData<List<Symptoms>> get() = _symptoms

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getSymptoms()
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