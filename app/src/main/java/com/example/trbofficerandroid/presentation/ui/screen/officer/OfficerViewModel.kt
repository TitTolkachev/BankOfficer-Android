package com.example.trbofficerandroid.presentation.ui.screen.officer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.domain.model.Officer
import com.example.trbofficerandroid.domain.usecase.BlockOfficerUseCase
import com.example.trbofficerandroid.domain.usecase.GetOfficerUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OfficerViewModel(
    savedStateHandle: SavedStateHandle,
    private val getOfficerUseCase: GetOfficerUseCase,
    private val blockOfficerUseCase: BlockOfficerUseCase,
) : ViewModel() {
    val id = savedStateHandle["id"] ?: ""

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _officer: MutableStateFlow<Officer?> = MutableStateFlow(null)
    val officer: StateFlow<Officer?> = _officer.asStateFlow()

    init {
        loadData()
    }

    fun blockUser() = viewModelScope.launch {
        _loading.update { true }
        try {
            blockOfficerUseCase(id)
        } catch (_: Exception) {
            _error.emit("Не удалось заблокировать пользователя")
        }
        delay(500)
        try {
            val officer = getOfficerUseCase(id)
            _officer.update { officer }
        } catch (_: Exception) {
            _error.emit("Не удалось получить данные пользователя")
        }
        _loading.update { false }
    }

    private fun loadData() = viewModelScope.launch {
        try {
            val officer = getOfficerUseCase(id)
            _officer.update { officer }
        } catch (_: Exception) {
            _error.emit("Не удалось получить данные пользователя")
        }
    }
}