package com.example.trbofficerandroid.presentation.ui.screen.addrate

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddRateViewModel : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _percentageRate = MutableStateFlow("")
    val percentageRate: StateFlow<String> = _percentageRate.asStateFlow()

    fun onNameChange(value: String) {
        _name.update { value }
    }

    fun onPercentageRateChange(value: String) {
        _percentageRate.update { value }
    }
}