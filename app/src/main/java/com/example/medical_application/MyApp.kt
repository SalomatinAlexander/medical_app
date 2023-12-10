package com.example.medical_application

import android.app.Application
import com.example.medical_application.di.AppComponent
import com.example.medical_application.di.DaggerAppComponent
import dagger.android.support.DaggerAppCompatActivity


class MyApp:Application() {



    lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
      mAppComponent =
            DaggerAppComponent.create()

    }
    companion object{
        internal lateinit var INSTANCE: MyApp
            private set
    }


}