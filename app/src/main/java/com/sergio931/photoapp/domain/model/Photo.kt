package com.sergio931.photoapp.domain.model

data class Photo(
    val id: String,
    val description: String?,
    val urlPhoto: String,
    val userName: String,
    val urlPhotoUser: String,
)