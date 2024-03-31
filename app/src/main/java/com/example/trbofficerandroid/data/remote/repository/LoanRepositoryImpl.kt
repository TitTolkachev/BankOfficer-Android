package com.example.trbofficerandroid.data.remote.repository

import android.util.Log
import com.example.trbofficerandroid.GetLoanListRequest
import com.example.trbofficerandroid.GetLoanRequest
import com.example.trbofficerandroid.LoanServiceGrpc
import com.example.trbofficerandroid.data.remote.mapper.LoanMapper.toDomain
import com.example.trbofficerandroid.domain.model.Loan
import com.example.trbofficerandroid.domain.model.LoanShort
import com.example.trbofficerandroid.domain.repository.LoanRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoanRepositoryImpl(
    private val api: LoanServiceGrpc.LoanServiceBlockingStub
) : LoanRepository {

    override suspend fun getLoanList(token: String): List<LoanShort> = withContext(Dispatchers.IO) {
        val request = GetLoanListRequest.newBuilder()
            .setToken(token)
            .build()
        return@withContext try {
            api.getLoanList(request).toDomain()
        } catch (e: Exception) {
            Log.e(TAG, "Ошибка при получении списка кредитов: ${e.message}")
            throw e
        }
    }

    override suspend fun getLoan(token: String, loanId: String): Loan =
        withContext(Dispatchers.IO) {
            val request = GetLoanRequest.newBuilder()
                .setToken(token)
                .setLoanId(loanId)
                .build()
            return@withContext try {
                api.getLoan(request).loan.toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при получении информации о кредите: ${e.message}")
                throw e
            }
        }

    companion object {
        private val TAG = ApplicationRepositoryImpl::class.simpleName
    }
}