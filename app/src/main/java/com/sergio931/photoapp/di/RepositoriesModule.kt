package com.sergio931.photoapp.di

import com.sergio931.photoapp.data.remote.PhotoApi
import com.sergio931.photoapp.data.repository.PhotoRepositoryImpl
import com.sergio931.photoapp.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun providePhotoRepository(
        api: PhotoApi
    ): PhotoRepository = PhotoRepositoryImpl(api)

}