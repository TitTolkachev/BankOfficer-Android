package com.example.trbofficerandroid.domain.model

data class ApplicationShort(
    val id: String,
    val loanTermInDays: Long,
    val issuedAmount: Long,
    val interestRate: Double
)
