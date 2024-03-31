package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.domain.model.Account
import com.example.trbofficerandroid.domain.repository.AccountRepository

class GetAccountListUseCase(
    private val authService: AuthService,
    private val repository: AccountRepository
) {
    suspend operator fun invoke(userId: String): List<Account> {
        val token = authService.getSignedInUser()?.token
            ?: throw Exception("Не авторизован")
        return repository.getAccountList(token = token, userId = userId)
    }
}