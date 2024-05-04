package com.example.trbofficerandroid.data.remote.repository

import android.util.Log
import com.example.trbofficerandroid.GetTransactionListRequest
import com.example.trbofficerandroid.GetTransactionsHistoryRequest
import com.example.trbofficerandroid.Transaction
import com.example.trbofficerandroid.TransactionServiceGrpc
import com.example.trbofficerandroid.data.remote.mapper.TransactionMapper.toDomain
import io.grpc.stub.ClientCallStreamObserver
import io.grpc.stub.ClientResponseObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import com.example.trbofficerandroid.domain.model.Transaction as TransactionDomain

class TransactionRepositoryImpl(
    private val api: TransactionServiceGrpc.TransactionServiceStub,
    private val api2: TransactionServiceGrpc.TransactionServiceBlockingStub,
) {
    fun getTransactionListFlow(): Flow<TransactionDomain> {
        val request = GetTransactionListRequest.newBuilder().build()
        val responseFlow: MutableSharedFlow<Result<Transaction>> = MutableSharedFlow()
        val observer = getObserver<GetTransactionListRequest, Transaction>(responseFlow)
        api.getTransactionList(request, observer)
        return responseFlow.mapNotNull {
            if (it.isSuccess)
                it.getOrNull()?.toDomain()
            else null
        }
    }

    suspend fun getTransactionsHistory(token: String, accountId: String): List<TransactionDomain> =
        withContext(Dispatchers.IO) {
            val request = GetTransactionsHistoryRequest.newBuilder()
                .setToken(token)
                .setAccountId(accountId)
                .build()
            return@withContext try {
                api2.getTransactionsHistory(request).toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при получении истории транзакций: ${e.message}")
                throw e
            }
        }

    private fun <T, U> getObserver(
        resultFlow: MutableSharedFlow<Result<U>>,
        onNext: ((U) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onCompleted: () -> Unit = {}
    ): ClientResponseObserver<T, U> {
        return object : ClientResponseObserver<T, U> {
            override fun beforeStart(requestStream: ClientCallStreamObserver<T>?) {}

            override fun onNext(value: U) {
                Log.d("Grpc", "got data $value")
                onNext?.let {
                    it(value)
                } ?: runBlocking {
                    resultFlow.emit(Result.success(value))
                }
            }

            override fun onError(t: Throwable?) {
                Log.d("Grpc", "request failed with error $t")
                onError?.let {
                    it(t!!)
                } ?: runBlocking {
                    resultFlow.emit(Result.failure(t!!))
                }
            }

            override fun onCompleted() {
                Log.d("Grpc", "request completed")
                onCompleted()
            }
        }
    }

    companion object {
        private val TAG = TransactionRepositoryImpl::class.simpleName
    }
}