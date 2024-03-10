package com.example.trbofficerandroid.domain.model

data class Account(
    val id: String,
    val type: AccountType,
    val balance: Long,
    val clientFullName: String,
    val externalClientId: String,
    val creationDate: Long,
    val closingDate: Long?,
    val isClosed: Boolean,
)
