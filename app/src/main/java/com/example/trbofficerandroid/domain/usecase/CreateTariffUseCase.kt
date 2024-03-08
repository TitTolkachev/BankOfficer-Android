package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.model.CreateTariff
import com.example.trbofficerandroid.domain.model.Tariff
import com.example.trbofficerandroid.domain.repository.TariffRepository

class CreateTariffUseCase(
    private val repository: TariffRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(tariff: CreateTariff): Tariff {
        val userId = getUserIdUseCase()
        return repository.createTariff(tariff.copy(officerId = userId))
    }
}