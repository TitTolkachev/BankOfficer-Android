package com.example.trbofficerandroid.domain.repository

import com.example.trbofficerandroid.domain.model.CreateTariff
import com.example.trbofficerandroid.domain.model.Tariff

interface TariffRepository {
    suspend fun getTariffList(token: String): List<Tariff>
    suspend fun createTariff(token: String, tariff: CreateTariff): Tariff
}