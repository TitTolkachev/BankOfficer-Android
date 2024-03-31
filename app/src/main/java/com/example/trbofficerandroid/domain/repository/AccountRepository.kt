package com.example.trbofficerandroid.domain.repository

import com.example.trbofficerandroid.domain.model.Account

interface AccountRepository {
    suspend fun getAccountList(token: String, userId: String): List<Account>
    suspend fun getAccount(token: String, accountId: String): Account
}