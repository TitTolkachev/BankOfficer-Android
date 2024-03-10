package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.ApplicationRepository

class GetApplicationUseCase(private val repository: ApplicationRepository) {
    suspend operator fun invoke(id: String) = repository.getApplication(id)
}