package com.example.trbofficerandroid.domain.repository

import com.example.trbofficerandroid.domain.model.CreateTariff
import com.example.trbofficerandroid.domain.model.Tariff

interface TariffRepository {
    suspend fun getTariffList(): List<Tariff>
    suspend fun createTariff(tariff: CreateTariff): Tariff
}