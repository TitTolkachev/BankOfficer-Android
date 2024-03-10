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
import com.example.trbofficerandroid.domain.usecase.GetOfficerListUseCase
import com.example.trbofficerandroid.domain.usecase.GetOfficerUseCase
import com.example.trbofficerandroid.domain.usecase.GetTariffListUseCase
import com.example.trbofficerandroid.domain.usecase.GetUserIdUseCase
import com.example.trbofficerandroid.domain.usecase.RejectApplicationUseCase
import com.example.trbofficerandroid.domain.usecase.SignInUseCase
import com.example.trbofficerandroid.domain.usecase.UpdateUserIdUseCase
import org.koin.dsl.module

val useCaseModule = module {

    // Account
    factory { GetAccountListUseCase(get()) }
    factory { GetAccountUseCase(get()) }

    // Application
    factory { GetApplicationListUseCase(get()) }
    factory { GetApplicationUseCase(get()) }
    factory { ApproveApplicationUseCase(get(), get()) }
    factory { RejectApplicationUseCase(get(), get()) }

    // Loan
    factory { GetLoanListUseCase(get()) }
    factory { GetAccountUseCase(get()) }

    // Tariff
    factory { GetTariffListUseCase(get()) }
    factory { CreateTariffUseCase(get(), get()) }

    // User
    factory { SignInUseCase(get(), get()) }
    factory { GetClientListUseCase(get()) }
    factory { GetOfficerListUseCase(get()) }
    factory { GetClientUseCase(get()) }
    factory { GetOfficerUseCase(get()) }
    factory { BlockClientUseCase(get(), get()) }
    factory { BlockOfficerUseCase(get(), get()) }
    factory { CreateClientUseCase(get(), get()) }
    factory { CreateOfficerUseCase(get(), get()) }

    // Other
    factory { GetUserIdUseCase(get()) }
    factory { UpdateUserIdUseCase(get()) }
}