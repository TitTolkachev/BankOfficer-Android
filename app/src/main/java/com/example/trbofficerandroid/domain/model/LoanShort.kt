package com.example.trbofficerandroid.domain.model

data class LoanShort(
    val id: String,
    val issuedDate: Long,
    val repaymentDate: Long,
    val amountDebt: Long,
    val interestRate: Double,
)
