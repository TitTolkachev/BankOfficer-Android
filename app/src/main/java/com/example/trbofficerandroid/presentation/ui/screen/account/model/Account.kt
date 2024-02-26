package com.example.trbofficerandroid.presentation.ui.screen.account.model

import com.example.trbofficerandroid.presentation.ui.screen.client.model.AccountState
import com.example.trbofficerandroid.presentation.ui.screen.client.model.AccountType

data class Account(
    val id: String,
    val number: String,
    val type: AccountType,
    val balance: Long,
    val state: AccountState
)
