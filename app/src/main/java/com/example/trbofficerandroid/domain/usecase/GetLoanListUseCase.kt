package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.domain.model.LoanShort
import com.example.trbofficerandroid.domain.repository.LoanRepository

class GetLoanListUseCase(
    private val authService: AuthService,
    private val repository: LoanRepository
) {
    suspend operator fun invoke(): List<LoanShort> {
        val token = authService.getSignedInUser()?.token
            ?: throw Exception("Не авторизован")
        return repository.getLoanList(token = token)
    }
}