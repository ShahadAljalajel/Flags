package com.example.flags.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flags.network.Flag
import com.example.flags.network.FlagsApiService
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus> = _status

    private val _photos = MutableLiveData<List<Flag>>()
    val photos: LiveData<List<Flag>> = _photos

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {

        viewModelScope.launch {
            _status.value = ApiStatus.LOADING

            try {
                _photos.value = FlagsApiService.FlagsApi.retrofitService.getPhotos().data
                _status.value = ApiStatus.DONE

            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _photos.value = listOf()
            }
        }

    }
}