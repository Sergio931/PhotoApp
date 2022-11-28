package com.sergio931.photoapp

import android.app.Application
import com.sergio931.photoapp.di.ApplicationComponent
import com.sergio931.photoapp.di.DaggerApplicationComponent

class PhotoApp : Application() {
    public lateinit var appComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory().create(this)
    }
}