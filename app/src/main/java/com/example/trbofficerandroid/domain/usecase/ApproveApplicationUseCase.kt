package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.model.Application
import com.example.trbofficerandroid.domain.repository.ApplicationRepository

class ApproveApplicationUseCase(
    private val repository: ApplicationRepository
) {
    suspend operator fun invoke(id: String): Application {
        TODO("Убрал id пользователя")
        val userId = throw Exception("Пользователь не авторизован")
        return repository.approveApplication(applicationId = id, userId = userId)
    }
}