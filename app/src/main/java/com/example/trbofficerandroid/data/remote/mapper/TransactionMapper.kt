package com.example.trbofficerandroid.data.remote.mapper

import com.example.trbofficerandroid.GetTransactionsHistoryResponse
import com.example.trbofficerandroid.TransactionParsed
import com.example.trbofficerandroid.data.remote.model.TransactionNotParsed
import com.example.trbofficerandroid.domain.model.Transaction
import kotlinx.serialization.json.Json
import com.example.trbofficerandroid.Transaction as TransactionProto

object TransactionMapper {
    fun TransactionProto.toDomain(): Transaction? {
        val full = Json.decodeFromString<TransactionNotParsed>(this.result)
        return if (full.state == "DONE") full.transaction else null
    }

    fun GetTransactionsHistoryResponse.toDomain(): List<Transaction> {
        return transactionsList.map {
            it.toDomain()
        }
    }

    private fun TransactionParsed.toDomain(): Transaction {
        return Transaction(
            id = id,
            externalId = externalId,
            date = date,
            payerAccountId = if (hasPayerAccountId()) payerAccountId else null,
            payeeAccountId = if (hasPayeeAccountId()) payeeAccountId else null,
            amount = amount,
            currency = currency,
            type = type,
        )
    }
}