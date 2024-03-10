package com.example.trbofficerandroid.domain.repository

import com.example.trbofficerandroid.domain.model.Account

interface AccountRepository {
    suspend fun getAccountList(id: String): List<Account>
    suspend fun getAccount(id: String): Account
}