package com.example.trbofficerandroid.data.remote.repository

import android.util.Log
import com.example.trbofficerandroid.CreateTariffRequest
import com.example.trbofficerandroid.GetTariffListRequest
import com.example.trbofficerandroid.TariffServiceGrpc.TariffServiceBlockingStub
import com.example.trbofficerandroid.data.remote.mapper.TariffMapper.toDomain
import com.example.trbofficerandroid.domain.model.CreateTariff
import com.example.trbofficerandroid.domain.model.Tariff
import com.example.trbofficerandroid.domain.repository.TariffRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TariffRepositoryImpl(private val api: TariffServiceBlockingStub) : TariffRepository {
    override suspend fun getTariffList(token: String): List<Tariff> = withContext(Dispatchers.IO) {
        val request = GetTariffListRequest.newBuilder()
            .setToken(token)
            .build()
        return@withContext try {
            api.getTariffList(request).toDomain()
        } catch (e: Exception) {
            Log.e(TAG, "Ошибка при получении списка тарифов: ${e.message}")
            throw e
        }
    }

    override suspend fun createTariff(token: String, tariff: CreateTariff): Tariff =
        withContext(Dispatchers.IO) {
            val request = CreateTariffRequest.newBuilder().apply {
                setToken(token)
                setName(tariff.name)
                setDescription(tariff.description)
                setInterestRate(tariff.interestRate)
            }.build()
            return@withContext try {
                val reply =
                    api.createTariff(request) ?: throw Exception("Ошибка при создании тарифа")
                reply.tariff.toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при создании тарифа: ${e.message}")
                throw e
            }
        }

    companion object {
        private val TAG = TariffRepositoryImpl::class.simpleName
    }
}