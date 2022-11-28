package com.sergio931.photoapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio931.photoapp.common.Resource
import com.sergio931.photoapp.domain.model.Photo
import com.sergio931.photoapp.domain.use_case.GetPhotosUseCase
import com.sergio931.photoapp.presentation.ui.HomeScreenEvent
import com.sergio931.photoapp.presentation.ui.HomeScreenState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {
    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    init {
        getPhotos()
    }

    fun deletePhoto(newList: List<Photo>) {
        _state.value = HomeScreenState(photosList = newList)
    }

    private fun getPhotos() {
        getPhotosUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = HomeScreenState(photosList = result.data ?: emptyList())

                }
                is Resource.Error -> {
                    _state.value = HomeScreenState(isError = true)
                }
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.GetPhotosList -> {
                getPhotos()
            }
        }
    }
}