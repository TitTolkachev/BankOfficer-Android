package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.ApplicationRepository

class GetApplicationListUseCase(private val repository: ApplicationRepository) {
    suspend operator fun invoke() = repository.getApplicationList()
}