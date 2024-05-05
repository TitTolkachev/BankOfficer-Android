package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.domain.model.Loan
import com.example.trbofficerandroid.domain.repository.LoanRepository

class GetLoanUseCase(
    private val authService: AuthService,
    private val repository: LoanRepository
) {
    suspend operator fun invoke(loanId: String): Loan {
        val token = authService.getSignedInUser()?.token
            ?: throw Exception("Не авторизован")
        val loan = repository.getLoan(token = token, loanId = loanId)
        return loan.copy(repayments = loan.repayments.sortedBy { it.date })
    }
}