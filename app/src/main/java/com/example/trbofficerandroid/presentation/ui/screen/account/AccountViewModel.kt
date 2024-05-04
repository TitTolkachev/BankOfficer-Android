package com.example.trbofficerandroid.presentation.ui.screen.account

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.data.remote.repository.TransactionRepositoryImpl
import com.example.trbofficerandroid.domain.model.Account
import com.example.trbofficerandroid.domain.model.Transaction
import com.example.trbofficerandroid.domain.usecase.GetAccountUseCase
import com.example.trbofficerandroid.domain.usecase.GetTransactionsHistoryUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountViewModel(
    savedStateHandle: SavedStateHandle,
    private val getAccountUseCase: GetAccountUseCase,
    private val transactionRepositoryImpl: TransactionRepositoryImpl,
    private val getTransactionsHistoryUseCase: GetTransactionsHistoryUseCase,
) : ViewModel() {
    val id = savedStateHandle["id"] ?: "UNKNOWN"

    private val _error = MutableSharedFlow<String>()
    val error = _error.shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _account: MutableStateFlow<Account?> = MutableStateFlow(null)
    val account: StateFlow<Account?> = _account.asStateFlow()

    private val _transactions: MutableStateFlow<List<Transaction>?> = MutableStateFlow(null)
    val transactions: StateFlow<List<Transaction>?> = _transactions.asStateFlow()

    init {
        loadData()
        listenToTransactions()
    }

    private fun loadData() = viewModelScope.launch {
        try {
            val account = getAccountUseCase(id)
            _account.update { account }

            val transactions = getTransactionsHistoryUseCase(id)
            _transactions.update { transactions }
        } catch (_: Exception) {
            _error.emit("Не удалось получить данные счета")
        }
    }

    private fun listenToTransactions() {
        transactionRepositoryImpl.getTransactionListFlow().onEach { transaction ->
            if (_transactions.value?.any { it.externalId == transaction.externalId } == true) {
                _transactions.update {
                    it?.minus(
                        it.first { t -> t.externalId == transaction.externalId }
                    )?.plus(transaction) ?: listOf(transaction)
                }
            } else {
                _transactions.update { it?.plus(transaction) ?: listOf(transaction) }
            }
        }.launchIn(viewModelScope)
    }
}