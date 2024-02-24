package com.example.trbofficerandroid

import android.app.Application
import com.example.trbofficerandroid.di.appModule
import com.example.trbofficerandroid.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, viewModelsModule)
        }
    }
}