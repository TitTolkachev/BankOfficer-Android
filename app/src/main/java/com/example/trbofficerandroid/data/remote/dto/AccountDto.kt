package com.example.trbofficerandroid.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    private val token: String,
    private val accountId: String,
)
