package com.example.trbofficerandroid.domain.model

data class Application(
    val id: String,
    val creationDate: Long,
    val updatedDateFinal: Long?,
    val loanTermInDays: Long,
    val issuedAmount: Long,
    val clientId: String,
    val officerId: String?,
    val state: ApplicationState,
    val tariff: Tariff
)
