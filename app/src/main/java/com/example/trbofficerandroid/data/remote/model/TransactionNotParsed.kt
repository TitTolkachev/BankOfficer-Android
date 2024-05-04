package com.example.trbofficerandroid.data.remote.model

import com.example.trbofficerandroid.domain.model.Transaction
import kotlinx.serialization.Serializable

@Serializable
data class TransactionNotParsed(
    val state: String,
    val transaction: Transaction,
)
