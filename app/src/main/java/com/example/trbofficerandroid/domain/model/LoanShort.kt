package com.example.trbofficerandroid.domain.model

data class LoanShort(
    val id: String,
    val clientFirstName: String,
    val clientLastName: String,
    val issuedAmount: Long,
    val amountDept: Long,
    val status: LoanStatus
)
