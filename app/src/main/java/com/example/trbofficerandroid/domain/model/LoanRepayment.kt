package com.example.trbofficerandroid.domain.model

data class LoanRepayment(
    val id: String,
    val date: Long,
    val amount: Long,
    val state: LoanRepaymentState,
)
