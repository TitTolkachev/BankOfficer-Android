package com.example.trbofficerandroid.di

import com.example.trbofficerandroid.domain.usecase.ApproveApplicationUseCase
import com.example.trbofficerandroid.domain.usecase.BlockClientUseCase
import com.example.trbofficerandroid.domain.usecase.BlockOfficerUseCase
import com.example.trbofficerandroid.domain.usecase.CreateClientUseCase
import com.example.trbofficerandroid.domain.usecase.CreateOfficerUseCase
import com.example.trbofficerandroid.domain.usecase.CreateTariffUseCase
import com.example.trbofficerandroid.domain.usecase.GetAccountListUseCase
import com.example.trbofficerandroid.domain.usecase.GetAccountUseCase
import com.example.trbofficerandroid.domain.usecase.GetApplicationListUseCase
import com.example.trbofficerandroid.domain.usecase.GetApplicationUseCase
import com.example.trbofficerandroid.domain.usecase.GetClientListUseCase
import com.example.trbofficerandroid.domain.usecase.GetClientUseCase
import com.example.trbofficerandroid.domain.usecase.GetLoanListUseCase
import com.example.trbofficerandroid.domain.usecase.GetLoanUseCase
import com.example.trbofficerandroid.domain.usecase.GetOfficerListUseCase
import com.example.trbofficerandroid.domain.usecase.GetOfficerUseCase
import com.example.trbofficerandroid.domain.usecase.GetTariffListUseCase
import com.example.trbofficerandroid.domain.usecase.RejectApplicationUseCase
import org.koin.dsl.module

val useCaseModule = module {

    // Account
    factory { GetAccountListUseCase(get()) }
    factory { GetAccountUseCase(get()) }

    // Application
    factory { GetApplicationListUseCase(get()) }
    factory { GetApplicationUseCase(get()) }
    factory { ApproveApplicationUseCase(get()) }
    factory { RejectApplicationUseCase(get()) }

    // Loan
    factory { GetLoanListUseCase(get()) }
    factory { GetLoanUseCase(get()) }

    // Tariff
    factory { GetTariffListUseCase(get()) }
    factory { CreateTariffUseCase(get()) }

    // User
    factory { GetClientListUseCase(get()) }
    factory { GetOfficerListUseCase(get()) }
    factory { GetClientUseCase(get()) }
    factory { GetOfficerUseCase(get()) }
    factory { BlockClientUseCase(get()) }
    factory { BlockOfficerUseCase(get()) }
    factory { CreateClientUseCase(get()) }
    factory { CreateOfficerUseCase(get()) }
}