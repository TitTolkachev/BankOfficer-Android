package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.LoanRepository

class GetLoanListUseCase(private val repository: LoanRepository) {
    suspend operator fun invoke() = repository.getLoanList()
}