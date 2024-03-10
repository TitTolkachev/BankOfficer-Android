package com.example.trbofficerandroid.domain.model

data class Loan(
    val id: String,
    val issuedDate: Long,
    val repaymentDate: Long,
    val issuedAmount: Long,
    val amountDebt: Long,
    val accruedPenny: Long,
    val loanTermInDays: Long,
    val clientId: String,
    val accountId: String,
    val state: LoanStatus,
    val tariff: Tariff,
    val repayments: List<LoanRepayment>,
)
