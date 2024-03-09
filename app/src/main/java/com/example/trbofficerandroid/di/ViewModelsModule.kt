package com.example.trbofficerandroid.di

import com.example.trbofficerandroid.presentation.ui.screen.addclient.AddClientViewModel
import com.example.trbofficerandroid.presentation.ui.screen.addofficer.AddOfficerViewModel
import com.example.trbofficerandroid.presentation.ui.screen.addtariff.AddTariffViewModel
import com.example.trbofficerandroid.presentation.ui.screen.home.HomeViewModel
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.LoanListViewModel
import com.example.trbofficerandroid.presentation.ui.screen.tarifflist.TariffListViewModel
import com.example.trbofficerandroid.presentation.ui.screen.userlist.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { HomeViewModel() }
    viewModel { UserListViewModel(get(), get()) }
    viewModel { TariffListViewModel() }
    viewModel { AddTariffViewModel() }
    viewModel { LoanListViewModel() }
    viewModel { AddClientViewModel(get()) }
    viewModel { AddOfficerViewModel(get()) }
}