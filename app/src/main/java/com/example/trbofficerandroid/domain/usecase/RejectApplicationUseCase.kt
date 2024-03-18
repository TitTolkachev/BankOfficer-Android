package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.model.Application
import com.example.trbofficerandroid.domain.repository.ApplicationRepository

class RejectApplicationUseCase(
    private val repository: ApplicationRepository
) {
    suspend operator fun invoke(id: String): Application {
        TODO("Убрал id пользователя")
        val userId = throw Exception("Пользователь не авторизован")
        return repository.rejectApplication(applicationId = id, userId = userId)
    }
}