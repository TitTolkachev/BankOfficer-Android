package com.example.trbofficerandroid.di

import com.example.trbofficerandroid.data.local.storage.AuthStorage
import org.koin.dsl.module


val appModule = module {

    // Local Auth Storage
    single { AuthStorage(get()) }
}