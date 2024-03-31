package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.domain.model.Tariff
import com.example.trbofficerandroid.domain.repository.TariffRepository

class GetTariffListUseCase(
    private val authService: AuthService,
    private val repository: TariffRepository
) {
    suspend operator fun invoke(): List<Tariff> {
        val token = authService.getSignedInUser()?.token
            ?: throw Exception("Не авторизован")
        return repository.getTariffList(token).reversed()
    }
}