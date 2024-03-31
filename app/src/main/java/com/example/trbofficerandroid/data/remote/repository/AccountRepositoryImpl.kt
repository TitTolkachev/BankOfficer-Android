package com.example.trbofficerandroid.data.remote.repository

import android.util.Log
import com.example.trbofficerandroid.AccountServiceGrpc
import com.example.trbofficerandroid.GetAccountListRequest
import com.example.trbofficerandroid.GetAccountRequest
import com.example.trbofficerandroid.data.remote.mapper.AccountMapper.toDomain
import com.example.trbofficerandroid.domain.model.Account
import com.example.trbofficerandroid.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountRepositoryImpl(
    private val api: AccountServiceGrpc.AccountServiceBlockingStub
) : AccountRepository {
    override suspend fun getAccountList(token: String, userId: String): List<Account> =
        withContext(Dispatchers.IO) {
            val request = GetAccountListRequest.newBuilder()
                .setToken(token)
                .setUserId(userId)
                .build()
            return@withContext try {
                api.getAccountList(request).accountsList.toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при получении заявки на кредит: ${e.message}")
                throw e
            }
        }

    override suspend fun getAccount(token: String, accountId: String): Account =
        withContext(Dispatchers.IO) {
            val request = GetAccountRequest.newBuilder()
                .setToken(token)
                .setAccountId(accountId)
                .build()
            return@withContext try {
                api.getAccount(request).account.toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при получении заявки на кредит: ${e.message}")
                throw e
            }
        }

    companion object {
        private val TAG = AccountRepositoryImpl::class.simpleName
    }
}