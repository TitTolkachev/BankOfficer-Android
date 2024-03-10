package com.example.trbofficerandroid.domain.repository

import com.example.trbofficerandroid.domain.model.Loan
import com.example.trbofficerandroid.domain.model.LoanShort

interface LoanRepository {

    suspend fun getLoanList(): List<LoanShort>
    suspend fun getLoan(id: String): Loan
}