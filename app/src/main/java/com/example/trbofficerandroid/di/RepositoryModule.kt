package com.example.trbofficerandroid.di

import com.example.trbofficerandroid.data.remote.repository.AccountRepositoryImpl
import com.example.trbofficerandroid.data.remote.repository.ApplicationRepositoryImpl
import com.example.trbofficerandroid.data.remote.repository.LoanRepositoryImpl
import com.example.trbofficerandroid.data.remote.repository.PrefsRepositoryImpl
import com.example.trbofficerandroid.data.remote.repository.TariffRepositoryImpl
import com.example.trbofficerandroid.data.remote.repository.TransactionRepositoryImpl
import com.example.trbofficerandroid.data.remote.repository.UserRepositoryImpl
import com.example.trbofficerandroid.domain.repository.AccountRepository
import com.example.trbofficerandroid.domain.repository.ApplicationRepository
import com.example.trbofficerandroid.domain.repository.LoanRepository
import com.example.trbofficerandroid.domain.repository.TariffRepository
import com.example.trbofficerandroid.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {

    // Account
    single<AccountRepository> { AccountRepositoryImpl(get()) }

    // Application
    single<ApplicationRepository> { ApplicationRepositoryImpl(get()) }

    // Loan
    single<LoanRepository> { LoanRepositoryImpl(get()) }

    // Tariff
    single<TariffRepository> { TariffRepositoryImpl(get()) }

    // Transactions
    single<TransactionRepositoryImpl> { TransactionRepositoryImpl(get()) }

    // User
    single<UserRepository> { UserRepositoryImpl(get()) }

    // Prefs
    single<PrefsRepositoryImpl> { PrefsRepositoryImpl(get()) }
}