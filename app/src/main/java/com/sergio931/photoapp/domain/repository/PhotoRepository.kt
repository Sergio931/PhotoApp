package com.sergio931.photoapp.domain.repository


import com.sergio931.photoapp.data.dto.PhotoDto


interface PhotoRepository {
    suspend fun getPhotos(): List<PhotoDto>
}