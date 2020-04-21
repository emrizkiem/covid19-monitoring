package dev.emrizkiem.covid19.ui.global

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import dev.emrizkiem.covid19.data.model.global.CaseUpdate
import dev.emrizkiem.covid19.data.model.global.DataResponse
import dev.emrizkiem.covid19.data.model.global.Location
import dev.emrizkiem.covid19.data.repository.global.GlobalRepository
import dev.emrizkiem.covid19.util.Mapper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class GlobalViewModel(
    private val repository: GlobalRepository,
    private val mapper: Mapper
) : ViewModel() {

    private val _overview = MutableLiveData<DataResponse>()
    val overview: LiveData<DataResponse> get() = _overview

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> get() = _location

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

//    init {
//        getOverviewGlobal()
//    }

    fun getDataWithLocation() {
        viewModelScope.launch {
            repository.loadOverview()
                .zip(repository.loadLocation()) { mainData, locations ->
                    CaseUpdate(mainData, locations ?: emptyArray())
                }
                .map {
                    it.copy().apply {
                        mainData = mapper.lastUpdateMapper(it.mainData as DataResponse)
                    }
                }
                .map {
                    it.copy().apply {
                        locations = mapper.locationNameMapper(it.locations)?.toTypedArray() ?: emptyArray()
                    }
                }
                .map {
                    it.copy().apply {
                        locations = mapper.locationLastUpdateMapper(it.locations)?.toTypedArray() ?: emptyArray()
                    }
                }
                .onStart { _state.value = true }
                .onCompletion { _state.value = false }
                .collect {
                    _overview.value = it.mainData

                    it.locations.forEach { location ->
                        delay(30)
                        _location.value = location
                    }
                }
        }
    }

//    private fun getOverviewGlobal() {
//        _state.value = true
//        viewModelScope.launch(Dispatchers.Main) {
//            val response = withContext(Dispatchers.IO) {
//                useCase.getOverviewGlobal()
//            }
//
//            when(response) {
//                is ResultState.Success -> {
//                    _overview.postValue(response.data)
//                }
//                is ResultState.Error -> {
//                    _error.postValue(response.error)
//                }
//            }
//            _state.value = false
//        }
//    }

//    fun getLocation() {
//        _state.value = true
//        viewModelScope.launch(Dispatchers.Main) {
//            val response = withContext(Dispatchers.IO) {
//                useCase.getLocation()
//            }
//
//            when(response) {
//                is ResultState.Success -> {
//                    _location.postValue(response.data)
//                }
//                is ResultState.Error -> {
//                    _error.postValue(response.error)
//                }
//            }
//            _state.value = false
//        }
//    }

}