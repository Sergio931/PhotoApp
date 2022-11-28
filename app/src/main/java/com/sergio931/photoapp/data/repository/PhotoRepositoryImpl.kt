package com.sergio931.photoapp.data.repository

import android.util.Log
import com.sergio931.photoapp.common.Resource
import com.sergio931.photoapp.common.toPhoto
import com.sergio931.photoapp.data.dto.PhotoDto
import com.sergio931.photoapp.data.remote.PhotoApi
import com.sergio931.photoapp.domain.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val api: PhotoApi) : PhotoRepository {
    override suspend fun getPhotos(): List<PhotoDto> {
        return withContext(Dispatchers.IO) {
            api.getPhotos()
        }
    }
}