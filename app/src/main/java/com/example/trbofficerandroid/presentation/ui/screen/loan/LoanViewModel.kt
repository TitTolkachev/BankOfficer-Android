package com.example.trbofficerandroid.presentation.ui.screen.loan

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.domain.model.Loan
import com.example.trbofficerandroid.domain.usecase.GetLoanUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoanViewModel(
    savedStateHandle: SavedStateHandle,
    private val getLoanUseCase: GetLoanUseCase
) : ViewModel() {
    val id = savedStateHandle["id"] ?: "UNKNOWN"

    private val _error = MutableSharedFlow<String>()
    val error = _error.shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _loan: MutableStateFlow<Loan?> = MutableStateFlow(null)
    val loan: StateFlow<Loan?> = _loan.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        try {
            val loan = getLoanUseCase(id)
            _loan.update { loan }
        } catch (_: Exception) {
            _error.emit("Не удалось получить данные по кредиту")
        }
    }
}