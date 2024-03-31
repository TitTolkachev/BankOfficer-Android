package com.example.trbofficerandroid.di

import com.example.trbofficerandroid.domain.usecase.ApproveApplicationUseCase
import com.example.trbofficerandroid.domain.usecase.BlockUserUseCase
import com.example.trbofficerandroid.domain.usecase.CreateTariffUseCase
import com.example.trbofficerandroid.domain.usecase.CreateUserUseCase
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
    factory { GetAccountListUseCase(get(), get()) }
    factory { GetAccountUseCase(get(), get()) }

    // Application
    factory { GetApplicationListUseCase(get(), get()) }
    factory { GetApplicationUseCase(get(), get()) }
    factory { ApproveApplicationUseCase(get(), get()) }
    factory { RejectApplicationUseCase(get(), get()) }

    // Loan
    factory { GetLoanListUseCase(get(), get()) }
    factory { GetLoanUseCase(get(), get()) }

    // Tariff
    factory { GetTariffListUseCase(get(), get()) }
    factory { CreateTariffUseCase(get(), get()) }

    // User
    factory { GetClientListUseCase(get(), get()) }
    factory { GetOfficerListUseCase(get(), get()) }
    factory { GetClientUseCase(get(), get()) }
    factory { GetOfficerUseCase(get(), get()) }
    factory { BlockUserUseCase(get(), get()) }
    factory { CreateUserUseCase(get(), get()) }
}