package com.example.trbofficerandroid.di

import com.example.trbofficerandroid.domain.usecase.CreateClientUseCase
import com.example.trbofficerandroid.domain.usecase.CreateOfficerUseCase
import com.example.trbofficerandroid.domain.usecase.CreateTariffUseCase
import com.example.trbofficerandroid.domain.usecase.GetClientListUseCase
import com.example.trbofficerandroid.domain.usecase.GetOfficerListUseCase
import com.example.trbofficerandroid.domain.usecase.GetTariffListUseCase
import com.example.trbofficerandroid.domain.usecase.GetUserIdUseCase
import org.koin.dsl.module

val useCaseModule = module {

    // Account

    // Application

    // Loan

    // Tariff
    factory { GetTariffListUseCase(get()) }
    factory { CreateTariffUseCase(get(), get()) }

    // User
    factory { GetClientListUseCase(get()) }
    factory { GetOfficerListUseCase(get()) }
    factory { CreateClientUseCase(get(), get()) }
    factory { CreateOfficerUseCase(get(), get()) }

    // Other
    factory { GetUserIdUseCase() }
}