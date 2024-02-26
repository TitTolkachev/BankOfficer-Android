package com.example.trbofficerandroid.presentation.ui.screen.client.model

data class AccountShort(
    val id: String,
    val number: String,
    val type: AccountType,
    val balance: Long,
    val state: AccountState
)
