package com.sergio931.photoapp.domain.use_case

import android.util.Log
import com.sergio931.photoapp.common.Resource
import com.sergio931.photoapp.common.toPhoto
import com.sergio931.photoapp.domain.model.Photo
import com.sergio931.photoapp.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    operator fun invoke(): Flow<Resource<List<Photo>>> = flow {
        try {
            emit(Resource.Loading<List<Photo>>())
            val response = repository.getPhotos()
            val photos = response.map { it.toPhoto() }
            emit(Resource.Success<List<Photo>>(photos))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Photo>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Photo>>("Couldn't reach server. Check your internet connection."))
        }
    }
}