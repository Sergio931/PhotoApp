package com.sergio931.photoapp.di

import com.sergio931.photoapp.data.repository.PhotoRepositoryImpl
import com.sergio931.photoapp.domain.repository.PhotoRepository
import com.sergio931.photoapp.domain.use_case.GetPhotosUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetPhotosUseCase(rep: PhotoRepository): GetPhotosUseCase =
        GetPhotosUseCase(rep)
}