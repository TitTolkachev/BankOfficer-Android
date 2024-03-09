package com.example.trbofficerandroid.presentation.ui.screen.addtariff

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddTariffViewModel : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _interestRate = MutableStateFlow("")
    val interestRate: StateFlow<String> = _interestRate.asStateFlow()

    fun onNameChange(value: String) {
        _name.update { value }
    }

    fun onInterestRateChange(value: String) {
        _interestRate.update { value }
    }
}