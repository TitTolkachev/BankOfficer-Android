package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.AccountRepository

class GetAccountListUseCase(private val repository: AccountRepository) {
    suspend operator fun invoke(id: String) = repository.getAccountList(id)
}