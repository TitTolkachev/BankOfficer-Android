package com.example.trbofficerandroid.di

import android.net.Uri
import com.example.trbofficerandroid.ApiServiceGrpc
import com.example.trbofficerandroid.ApiServiceGrpc.ApiServiceBlockingStub
import io.grpc.ManagedChannelBuilder
import org.koin.dsl.module

val networkModule = module {

    single<ApiServiceBlockingStub> {
        val url = Uri.parse("http://188.235.125.159:5083/")
        val channel = ManagedChannelBuilder.forAddress(url.host, url.port).usePlaintext().build()
        ApiServiceGrpc.newBlockingStub(channel)
    }
}