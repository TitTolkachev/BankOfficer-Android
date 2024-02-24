package com.example.hbofficerandroid.di

import com.example.hbofficerandroid.presentation.ui.screen.home.HomeViewModel
import com.example.hbofficerandroid.presentation.ui.screen.userlist.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { HomeViewModel() }
    viewModel { UserListViewModel() }
}