package com.example.trbofficerandroid.presentation.ui.screen.addofficer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.presentation.ui.screen.addofficer.model.AddOfficerShort
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddOfficerViewModel : ViewModel() {

    private val _navigateBack = MutableSharedFlow<Unit>()
    val navigateBack = _navigateBack.asSharedFlow()

    private val _user = MutableStateFlow(AddOfficerShort())
    val user: StateFlow<AddOfficerShort> = _user.asStateFlow()

    fun onFirstNameChange(newValue: String) {
        _user.update { it.copy(firstName = newValue) }
    }

    fun onLastNameChange(newValue: String) {
        _user.update { it.copy(lastName = newValue) }
    }

    fun onPatronymicNameChange(newValue: String) {
        _user.update { it.copy(patronymicName = newValue) }
    }

    fun onPhoneNumberChange(newValue: String) {
        _user.update { it.copy(phoneNumber = newValue) }
    }

    fun onAddressChange(newValue: String) {
        _user.update { it.copy(address = newValue) }
    }

    fun onPassportNumberChange(newValue: String) {
        _user.update { it.copy(passportNumber = newValue) }
    }

    fun onPassportSeriesChange(newValue: String) {
        _user.update { it.copy(passportSeries = newValue) }
    }

    fun onEmailChange(newValue: String) {
        _user.update { it.copy(email = newValue) }
    }

    fun onPasswordChange(newValue: String) {
        _user.update { it.copy(password = newValue) }
    }

    fun onCreateUser() {

        // TODO: Validate and Create User

        viewModelScope.launch {
            _navigateBack.emit(Unit)
        }
    }
}