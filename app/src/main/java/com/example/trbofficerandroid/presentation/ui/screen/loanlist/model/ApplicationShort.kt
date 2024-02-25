package com.example.trbofficerandroid.presentation.ui.screen.loanlist.model

data class ApplicationShort(
    val id: String,
    val loanTermInDays: Long,
    val issuedAmount: Long,
    val percentageRate: Double
)
