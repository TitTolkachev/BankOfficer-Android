package com.example.trbofficerandroid.data.remote.repository

import android.util.Log
import com.example.trbofficerandroid.GetTransactionListRequest
import com.example.trbofficerandroid.Transaction
import com.example.trbofficerandroid.TransactionServiceGrpc
import io.grpc.stub.ClientCallStreamObserver
import io.grpc.stub.ClientResponseObserver
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.runBlocking

class TransactionRepositoryImpl(private val api: TransactionServiceGrpc.TransactionServiceStub) {

    fun getTransactionListFlow(): SharedFlow<Result<Transaction>> {
        val request = GetTransactionListRequest.newBuilder().build()
        val responseFlow: MutableSharedFlow<Result<Transaction>> = MutableSharedFlow()
        val observer = getObserver<GetTransactionListRequest, Transaction>(responseFlow)
        api.getTransactionList(request, observer)
        return responseFlow.asSharedFlow()
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
}