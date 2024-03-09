package com.example.trbofficerandroid.presentation.ui.screen.addtariff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.domain.model.CreateTariff
import com.example.trbofficerandroid.domain.usecase.CreateTariffUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddTariffViewModel(
    private val createTariffUseCase: CreateTariffUseCase
) : ViewModel() {

    private val _navigateBack = MutableSharedFlow<Unit>()
    val navigateBack = _navigateBack.asSharedFlow()

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _interestRate = MutableStateFlow("")
    val interestRate: StateFlow<String> = _interestRate.asStateFlow()

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    fun onNameChange(value: String) {
        _name.update { value }
    }

    fun onInterestRateChange(value: String) {
        val rate = value.replace(',', '.')
        if (value.isBlank() || rate.toDoubleOrNull() != null)
            _interestRate.update { rate.trim() }
    }

    fun onDescriptionChange(value: String) {
        _description.update { value }
    }

    fun onBackClick() {
        viewModelScope.launch { _navigateBack.emit(Unit) }
    }

    fun onAddTariff() {
        viewModelScope.launch {
            val name = _name.value
            val description = _description.value
            val interestRate = _interestRate.value.toDoubleOrNull()
            if (name.isBlank())
                _error.emit("Не указано название тарифа")
            else if (interestRate == null)
                _error.emit("Не указана ставка по тарифу")
            else {
                _loading.update { true }
                val tariff = CreateTariff(
                    name = name,
                    description = description,
                    interestRate = interestRate
                )
                try {
                    createTariffUseCase(tariff = tariff)
                    _navigateBack.emit(Unit)
                } catch (_: Exception) {
                    _error.emit("Ошибка при создании")
                }
                _loading.update { false }
            }

        }
    }
}