package com.example.trbofficerandroid.presentation.ui.screen.application

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.domain.model.Application
import com.example.trbofficerandroid.domain.usecase.ApproveApplicationUseCase
import com.example.trbofficerandroid.domain.usecase.GetApplicationUseCase
import com.example.trbofficerandroid.domain.usecase.RejectApplicationUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ApplicationViewModel(
    savedStateHandle: SavedStateHandle,
    private val getApplicationUseCase: GetApplicationUseCase,
    private val approveApplicationUseCase: ApproveApplicationUseCase,
    private val rejectApplicationUseCase: RejectApplicationUseCase,
) : ViewModel() {
    val id = savedStateHandle["id"] ?: "UNKNOWN"

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _application: MutableStateFlow<Application?> = MutableStateFlow(null)
    val application: StateFlow<Application?> = _application.asStateFlow()

    init {
        loadData()
    }

    fun approveApplication() = viewModelScope.launch {
        _loading.update { true }
        try {
            val application = approveApplicationUseCase(id)
            _application.update { application }
        } catch (_: Exception) {
            _error.emit("Не удалось одобрить заявку")
        }
        _loading.update { false }
    }

    fun rejectApplication() = viewModelScope.launch {
        _loading.update { true }
        try {
            val application = rejectApplicationUseCase(id)
            _application.update { application }
        } catch (_: Exception) {
            _error.emit("Не удалось отклонить заявку")
        }
        _loading.update { false }
    }

    private fun loadData() = viewModelScope.launch {
        try {
            val application = getApplicationUseCase(id)
            _application.update { application }
        } catch (_: Exception) {
            _error.emit("Не удалось получить данные о заявке")
        }
    }
}