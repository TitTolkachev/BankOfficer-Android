package com.example.trbofficerandroid.presentation.ui.screen.account

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.trbofficerandroid.domain.usecase.GetAccountUseCase

class AccountViewModel(
    savedStateHandle: SavedStateHandle,
    private val getAccountUseCase: GetAccountUseCase,
) : ViewModel() {


}