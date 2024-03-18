package com.example.trbofficerandroid.di

import com.example.trbofficerandroid.data.remote.AuthService
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

    // Remote Auth Service
    single {
        AuthService(
            androidApplication().applicationContext,
            Firebase.auth,
            Identity.getSignInClient(androidApplication().applicationContext)
        )
    }
}