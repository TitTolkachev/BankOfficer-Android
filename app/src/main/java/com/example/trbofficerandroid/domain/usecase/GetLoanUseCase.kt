package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.LoanRepository

class GetLoanUseCase(private val repository: LoanRepository) {
    suspend operator fun invoke(id: String) = repository.getLoan(id)
}