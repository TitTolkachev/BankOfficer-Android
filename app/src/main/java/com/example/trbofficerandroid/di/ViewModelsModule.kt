package com.example.trbofficerandroid.di

import com.example.trbofficerandroid.presentation.ui.MainViewModel
import com.example.trbofficerandroid.presentation.ui.screen.account.AccountViewModel
import com.example.trbofficerandroid.presentation.ui.screen.addclient.AddClientViewModel
import com.example.trbofficerandroid.presentation.ui.screen.addofficer.AddOfficerViewModel
import com.example.trbofficerandroid.presentation.ui.screen.addtariff.AddTariffViewModel
import com.example.trbofficerandroid.presentation.ui.screen.application.ApplicationViewModel
import com.example.trbofficerandroid.presentation.ui.screen.client.ClientViewModel
import com.example.trbofficerandroid.presentation.ui.screen.home.HomeViewModel
import com.example.trbofficerandroid.presentation.ui.screen.loan.LoanViewModel
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.LoanListViewModel
import com.example.trbofficerandroid.presentation.ui.screen.more.MoreViewModel
import com.example.trbofficerandroid.presentation.ui.screen.officer.OfficerViewModel
import com.example.trbofficerandroid.presentation.ui.screen.signin.SignInViewModel
import com.example.trbofficerandroid.presentation.ui.screen.tariff.TariffViewModel
import com.example.trbofficerandroid.presentation.ui.screen.tarifflist.TariffListViewModel
import com.example.trbofficerandroid.presentation.ui.screen.userlist.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { HomeViewModel() }
    viewModel { UserListViewModel(get(), get()) }
    viewModel { TariffListViewModel(get()) }
    viewModel { AddTariffViewModel(get()) }
    viewModel { LoanListViewModel(get(), get()) }
    viewModel { AddClientViewModel(get()) }
    viewModel { AddOfficerViewModel(get()) }
    viewModel { TariffViewModel(get()) }
    viewModel { OfficerViewModel(get(), get(), get()) }
    viewModel { MainViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { MoreViewModel(get()) }
    viewModel { ApplicationViewModel(get(), get(), get(), get()) }
    viewModel { ClientViewModel(get(), get(), get(), get()) }
    viewModel { LoanViewModel(get(), get()) }
    viewModel { AccountViewModel(get(), get()) }
}