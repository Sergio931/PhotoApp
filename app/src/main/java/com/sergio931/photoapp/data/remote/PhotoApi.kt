package com.sergio931.photoapp.data.remote

import com.sergio931.photoapp.common.Constants.AccessKey
import com.sergio931.photoapp.data.dto.PhotoDto
import retrofit2.http.GET

interface PhotoApi {
    @GET("photos/?client_id=$AccessKey")
    suspend fun getPhotos(): List<PhotoDto>
}