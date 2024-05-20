package com.example.trbofficerandroid.di

import android.net.Uri
import com.example.trbofficerandroid.AccountServiceGrpc
import com.example.trbofficerandroid.AccountServiceGrpc.AccountServiceBlockingStub
import com.example.trbofficerandroid.ApplicationServiceGrpc
import com.example.trbofficerandroid.ApplicationServiceGrpc.ApplicationServiceBlockingStub
import com.example.trbofficerandroid.LoanServiceGrpc
import com.example.trbofficerandroid.LoanServiceGrpc.LoanServiceBlockingStub
import com.example.trbofficerandroid.RatingServiceGrpc
import com.example.trbofficerandroid.RatingServiceGrpc.RatingServiceBlockingStub
import com.example.trbofficerandroid.TariffServiceGrpc
import com.example.trbofficerandroid.TariffServiceGrpc.TariffServiceBlockingStub
import com.example.trbofficerandroid.TransactionServiceGrpc
import com.example.trbofficerandroid.TransactionServiceGrpc.TransactionServiceBlockingStub
import com.example.trbofficerandroid.TransactionServiceGrpc.TransactionServiceStub
import com.example.trbofficerandroid.UserServiceGrpc
import com.example.trbofficerandroid.UserServiceGrpc.UserServiceBlockingStub
import com.example.trbofficerandroid.data.remote.api.PrefsApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.grpc.Channel
import io.grpc.ManagedChannelBuilder
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val networkModule = module {

    // --------------------------------------
    // Grpc

    single<Channel> {
        val url = Uri.parse("http://176.209.187.106:50083/")
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
    single<TransactionServiceStub> {
        TransactionServiceGrpc.newStub(get())
    }
    single<TransactionServiceBlockingStub> {
        TransactionServiceGrpc.newBlockingStub(get())
    }
    single<RatingServiceBlockingStub> {
        RatingServiceGrpc.newBlockingStub(get())
    }


    // --------------------------------------
    // REST

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }
    single<Retrofit> {
        val builder = Retrofit.Builder()
            .baseUrl("http://81.19.137.198:8085/api/preferences/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))

        val client: OkHttpClient = get()
        builder.client(client).build()
    }
    single { get<Retrofit>().create(PrefsApi::class.java) }
}