package com.example.trbofficerandroid.di

import com.example.trbofficerandroid.domain.usecase.CreateClientUseCase
import com.example.trbofficerandroid.domain.usecase.CreateOfficerUseCase
import com.example.trbofficerandroid.domain.usecase.GetClientListUseCase
import com.example.trbofficerandroid.domain.usecase.GetOfficerListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    // Account

    // Application

    // Loan

    // Rate

    // User
    factory { GetClientListUseCase(get()) }
    factory { GetOfficerListUseCase(get()) }
    factory { CreateClientUseCase(get()) }
    factory { CreateOfficerUseCase(get()) }
}