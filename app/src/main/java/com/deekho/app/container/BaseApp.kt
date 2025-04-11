package com.deekho.app.container

import android.app.Application
import com.deekho.app.koin.repoModule
import com.deekho.app.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        
        startKoin{
            androidContext(this@BaseApp)
            modules(listOf(viewModelModule, repoModule))
        }

    }
}