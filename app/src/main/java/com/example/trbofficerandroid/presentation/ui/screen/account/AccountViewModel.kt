package com.example.trbofficerandroid.presentation.ui.screen.account

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.data.remote.repository.TransactionRepositoryImpl
import com.example.trbofficerandroid.domain.model.Account
import com.example.trbofficerandroid.domain.usecase.GetAccountUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountViewModel(
    savedStateHandle: SavedStateHandle,
    private val getAccountUseCase: GetAccountUseCase,
    transactionRepositoryImpl: TransactionRepositoryImpl,
) : ViewModel() {
    val id = savedStateHandle["id"] ?: "UNKNOWN"

    private val _error = MutableSharedFlow<String>()
    val error = _error.shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _account: MutableStateFlow<Account?> = MutableStateFlow(null)
    val account: StateFlow<Account?> = _account.asStateFlow()

    private val flow = transactionRepositoryImpl.getTransactionListFlow().onEach { result ->
        if (result.isSuccess) {
            val transaction = result.getOrNull()?.result ?: return@onEach
            _transactions.update { it.plus(transaction) }
        }
    }.shareIn(viewModelScope, SharingStarted.Eagerly)

    private val _transactions: MutableStateFlow<List<String>> = MutableStateFlow(listOf())
    val transactions: StateFlow<List<String>> = _transactions.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        try {
            val account = getAccountUseCase(id)
            _account.update { account }
        } catch (_: Exception) {
            _error.emit("Не удалось получить данные счета")
        }
    }
}