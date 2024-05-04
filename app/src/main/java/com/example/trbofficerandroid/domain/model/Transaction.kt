package com.example.trbofficerandroid.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val id: String,
    val externalId: String,
    val date: Long,
    val payerAccountId: String? = null,
    val payeeAccountId: String? = null,
    val amount: Double,
    val currency: String,
    val type: String,
)
