package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.data.remote.repository.TransactionRepositoryImpl
import com.example.trbofficerandroid.domain.model.Transaction

class GetTransactionsHistoryUseCase(
    private val authService: AuthService,
    private val repository: TransactionRepositoryImpl
) {
    suspend operator fun invoke(accountId: String): List<Transaction> {
        val token = authService.getSignedInUser()?.token
            ?: throw Exception("Не авторизован")
        return repository.getTransactionsHistory(token, accountId)
    }
}