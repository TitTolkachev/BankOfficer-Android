package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.TariffRepository

class GetTariffListUseCase(private val repository: TariffRepository) {
    suspend operator fun invoke() = repository.getTariffList().reversed()
}