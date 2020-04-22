package dev.emrizkiem.covid19.ui.global

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emrizkiem.covid19.data.model.global.DataResponse
import dev.emrizkiem.covid19.data.model.global.Location
import dev.emrizkiem.covid19.domain.global.GlobalUseCase
import dev.emrizkiem.covid19.util.ResultState
import kotlinx.coroutines.*

@ExperimentalCoroutinesApi
class GlobalViewModel(
    private val useCase: GlobalUseCase
) : ViewModel() {
    private val _overview = MutableLiveData<DataResponse>()
    val overview: LiveData<DataResponse> get() = _overview

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> get() = _location

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getDataWithLocation() {
        _state.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                useCase.getGlobalOverview()
            }

            when (response) {
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

    fun getLocationGlobal() {
        _state.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                useCase.getLocationGlobal()
            }

            when (response) {
                is ResultState.Success -> {
                    response.data?.forEach { location ->
                        delay(30)
                        _location.postValue(location)
                    }
                }
                is ResultState.Error -> {
                    _error.postValue(response.error)
                }
            }
            _state.value = false
        }
    }
}