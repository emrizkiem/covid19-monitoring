package dev.emrizkiem.covid19.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emrizkiem.covid19.data.model.explore.ArticlesItem
import dev.emrizkiem.covid19.domain.explore.ExploreUseCase
import dev.emrizkiem.covid19.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExploreViewModel(private val useCase: ExploreUseCase) : ViewModel() {

    private val _explore = MutableLiveData<List<ArticlesItem>>()
    val explore: LiveData<List<ArticlesItem>> get() = _explore

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getExplore()
    }

    fun getExplore() {
        _state.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                useCase.getExplore()
            }

            when(response) {
                is ResultState.Success -> {
                    _explore.postValue(response.data)
                }
                is ResultState.Error -> {
                    _error.postValue(response.error)
                }
            }
            _state.value = false
        }
    }
}