package com.example.trbofficerandroid.presentation.ui.screen.client

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.domain.model.Account
import com.example.trbofficerandroid.domain.model.Client
import com.example.trbofficerandroid.domain.model.CreditRating
import com.example.trbofficerandroid.domain.usecase.BlockUserUseCase
import com.example.trbofficerandroid.domain.usecase.GetAccountListUseCase
import com.example.trbofficerandroid.domain.usecase.GetClientUseCase
import com.example.trbofficerandroid.domain.usecase.GetUserRatingUseCase
import com.example.trbofficerandroid.domain.usecase.UpdateUserRatingUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ClientViewModel(
    savedStateHandle: SavedStateHandle,
    private val getClientUseCase: GetClientUseCase,
    private val getAccountListUseCase: GetAccountListUseCase,
    private val blockUserUseCase: BlockUserUseCase,
    private val updateUserRatingUseCase: UpdateUserRatingUseCase,
    private val getUserRatingUseCase: GetUserRatingUseCase,
) : ViewModel() {
    val id = savedStateHandle["id"] ?: "UNKNOWN"

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _loadingRating = MutableStateFlow(false)
    val loadingRating: StateFlow<Boolean> = _loadingRating.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _client: MutableStateFlow<Client?> = MutableStateFlow(null)
    val client: StateFlow<Client?> = _client.asStateFlow()

    private val _rating: MutableStateFlow<CreditRating?> = MutableStateFlow(null)
    val rating: StateFlow<CreditRating?> = _rating.asStateFlow()

    private val _accounts: MutableStateFlow<List<Account>?> = MutableStateFlow(null)
    val accounts: StateFlow<List<Account>?> = _accounts.asStateFlow()

    init {
        loadData()
        loadUserRating()
    }

    fun blockUser() = viewModelScope.launch {
        _loading.update { true }
        try {
            blockUserUseCase(id)
        } catch (_: Exception) {
            _error.emit("Не удалось заблокировать пользователя")
        }
        delay(500)
        try {
            val client = getClientUseCase(id)
            val accounts = getAccountListUseCase(id)
            _client.update { client }
            _accounts.update { accounts }
        } catch (_: Exception) {
            _error.emit("Не удалось получить данные пользователя")
        }
        _loading.update { false }
    }

    private fun loadData() = viewModelScope.launch {
        try {
            val client = getClientUseCase(id)
            val accounts = getAccountListUseCase(id)
            delay(1000)
            _client.update { client }
            _accounts.update { accounts }
        } catch (_: Exception) {
            _error.emit("Не удалось получить данные пользователя")
        }
    }

    private fun loadUserRating() = viewModelScope.launch {
        _loadingRating.update { true }
        try {
            val rating = getUserRatingUseCase(id)
            delay(1000)
            _rating.update { rating }
        } catch (_: Exception) {
        }
        _loadingRating.update { false }
    }

    fun updateUserRating() = viewModelScope.launch {
        _loadingRating.update { true }
        try {
            val rating = updateUserRatingUseCase(id)
            delay(1000)
            _rating.update { rating }
        } catch (_: Exception) {
            _error.emit("Не удалось обновить кредитный рейтинг пользователя")
        }
        _loadingRating.update { false }
    }
}