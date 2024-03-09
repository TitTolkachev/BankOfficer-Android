package com.example.trbofficerandroid.di

import com.example.trbofficerandroid.presentation.ui.screen.addclient.AddClientViewModel
import com.example.trbofficerandroid.presentation.ui.screen.addofficer.AddOfficerViewModel
import com.example.trbofficerandroid.presentation.ui.screen.addtariff.AddTariffViewModel
import com.example.trbofficerandroid.presentation.ui.screen.home.HomeViewModel
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.LoanListViewModel
import com.example.trbofficerandroid.presentation.ui.screen.officer.OfficerViewModel
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
    viewModel { LoanListViewModel() }
    viewModel { AddClientViewModel(get()) }
    viewModel { AddOfficerViewModel(get()) }
    viewModel { TariffViewModel(get()) }
    viewModel { OfficerViewModel(get(), get(), get()) }
}