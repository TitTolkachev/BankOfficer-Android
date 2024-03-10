package com.example.trbofficerandroid.presentation.ui.screen.addclient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.domain.model.Sex
import com.example.trbofficerandroid.domain.usecase.CreateClientUseCase
import com.example.trbofficerandroid.presentation.ui.common.mapper.UserMapper.toDomain
import com.example.trbofficerandroid.presentation.ui.screen.addclient.model.AddClient
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale.getDefault

class AddClientViewModel(
    private val createClientUseCase: CreateClientUseCase
) : ViewModel() {

    private val _navigateBack = MutableSharedFlow<Unit>()
    val navigateBack = _navigateBack.asSharedFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _user = MutableStateFlow(AddClient())
    val user: StateFlow<AddClient> = _user.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _datePickerVisible = MutableStateFlow(false)
    val datePickerVisible: StateFlow<Boolean> = _datePickerVisible.asStateFlow()

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

    fun onBirthDateChange(newValue: Long) {
        _user.update {
            it.copy(
                birthDate = SimpleDateFormat("d MMMM yyyy", getDefault()).format(Date(newValue)),
                birthDateMs = newValue
            )
        }
    }

    fun onSexChange(newValue: Sex) {
        _user.update { it.copy(sex = newValue) }
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
        if (_loading.value)
            return

        viewModelScope.launch {
            _loading.update { true }
            try {
                createClientUseCase(_user.value.toDomain())
                _navigateBack.emit(Unit)
            } catch (_: Exception) {
                _error.emit("Ошибка при создании")
            }
            _loading.update { false }
        }
    }

    fun showDatePicker() {
        _datePickerVisible.update { true }
    }

    fun hideDatePicker() {
        _datePickerVisible.update { false }
    }
}