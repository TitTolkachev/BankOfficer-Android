package com.example.trbofficerandroid.domain.repository

import com.example.trbofficerandroid.domain.model.Loan
import com.example.trbofficerandroid.domain.model.LoanShort

interface LoanRepository {
    suspend fun getLoanList(token: String): List<LoanShort>
    suspend fun getLoan(token: String, loanId: String): Loan
}