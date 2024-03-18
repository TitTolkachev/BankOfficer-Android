package com.example.trbofficerandroid.di

import android.net.Uri
import com.example.trbofficerandroid.AccountServiceGrpc
import com.example.trbofficerandroid.AccountServiceGrpc.AccountServiceBlockingStub
import com.example.trbofficerandroid.ApplicationServiceGrpc
import com.example.trbofficerandroid.ApplicationServiceGrpc.ApplicationServiceBlockingStub
import com.example.trbofficerandroid.LoanServiceGrpc
import com.example.trbofficerandroid.LoanServiceGrpc.LoanServiceBlockingStub
import com.example.trbofficerandroid.TariffServiceGrpc
import com.example.trbofficerandroid.TariffServiceGrpc.TariffServiceBlockingStub
import com.example.trbofficerandroid.UserServiceGrpc
import com.example.trbofficerandroid.UserServiceGrpc.UserServiceBlockingStub
import io.grpc.Channel
import io.grpc.ManagedChannelBuilder
import org.koin.dsl.module

val networkModule = module {

    single<Channel> {
        val url = Uri.parse("http://147.45.69.93:8083/")
        ManagedChannelBuilder.forAddress(url.host, url.port).usePlaintext().build()
    }

    single<AccountServiceBlockingStub> {
        AccountServiceGrpc.newBlockingStub(get())
    }
    single<ApplicationServiceBlockingStub> {
        ApplicationServiceGrpc.newBlockingStub(get())
    }
    single<LoanServiceBlockingStub> {
        LoanServiceGrpc.newBlockingStub(get())
    }
    single<TariffServiceBlockingStub> {
        TariffServiceGrpc.newBlockingStub(get())
    }
    single<UserServiceBlockingStub> {
        UserServiceGrpc.newBlockingStub(get())
    }
}