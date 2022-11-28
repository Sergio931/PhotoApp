package com.sergio931.photoapp.presentation.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.sergio931.photoapp.domain.model.Photo

data class HomeScreenState(
    val photosList: List<Photo> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isEmpty: Boolean = false,
)
