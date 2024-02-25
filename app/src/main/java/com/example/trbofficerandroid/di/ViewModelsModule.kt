package com.example.trbofficerandroid.di

import com.example.trbofficerandroid.presentation.ui.screen.addrate.AddRateViewModel
import com.example.trbofficerandroid.presentation.ui.screen.home.HomeViewModel
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.LoanListViewModel
import com.example.trbofficerandroid.presentation.ui.screen.ratelist.RateListViewModel
import com.example.trbofficerandroid.presentation.ui.screen.userlist.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { HomeViewModel() }
    viewModel { UserListViewModel() }
    viewModel { RateListViewModel() }
    viewModel { AddRateViewModel() }
    viewModel { LoanListViewModel() }
}