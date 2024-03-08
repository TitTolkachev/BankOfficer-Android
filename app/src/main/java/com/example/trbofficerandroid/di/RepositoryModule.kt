package com.example.trbofficerandroid.di

import com.example.trbofficerandroid.data.remote.repository.TariffRepositoryImpl
import com.example.trbofficerandroid.data.remote.repository.UserRepositoryImpl
import com.example.trbofficerandroid.domain.repository.TariffRepository
import com.example.trbofficerandroid.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {

    // Account

    // Application

    // Loan

    // Tariff
    single<TariffRepository> { TariffRepositoryImpl(get()) }

    // User
    single<UserRepository> { UserRepositoryImpl(get()) }
}