package com.sergio931.photoapp.di

import android.app.Application
import android.content.Context
import com.sergio931.photoapp.di.viewmodel.ViewModelModule
import com.sergio931.photoapp.presentation.MainActivity

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoriesModule::class, UseCaseModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}