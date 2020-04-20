package dev.emrizkiem.covid19.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emrizkiem.covid19.data.model.home.CovidDetail
import dev.emrizkiem.covid19.domain.home.HomeUseCase
import dev.emrizkiem.covid19.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val useCase: HomeUseCase): ViewModel() {

    private val _detail = MutableLiveData<List<CovidDetail>>()
    val detail: LiveData<List<CovidDetail>> get() = _detail

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getDetail()
    }

    private fun getDetail() {
        _state.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                useCase.getDetail()
            }

            when(response) {
                is ResultState.Success -> {
                    _detail.postValue(response.data)
                }
                is ResultState.Error -> {
                    _error.postValue(response.error)
                }
            }
            _state.value = false
        }
    }
}