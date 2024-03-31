package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.domain.model.CreateTariff
import com.example.trbofficerandroid.domain.model.Tariff
import com.example.trbofficerandroid.domain.repository.TariffRepository

class CreateTariffUseCase(
    private val authService: AuthService,
    private val repository: TariffRepository
) {
    suspend operator fun invoke(tariff: CreateTariff): Tariff {
        val token = authService.getSignedInUser()?.token
            ?: throw Exception("Не авторизован")
        return repository.createTariff(token = token, tariff = tariff)
    }
}