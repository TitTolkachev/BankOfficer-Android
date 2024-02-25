package com.example.trbofficerandroid.presentation.ui.screen.loanlist.model

data class LoanShort(
    val id: String,
    val clientFirstName: String,
    val clientLastName: String,
    val issuedAmount: Long,
    val amountDept: Long,
    val status: LoanStatus
)
