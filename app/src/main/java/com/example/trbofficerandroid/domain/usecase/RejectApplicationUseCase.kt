package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.model.Application
import com.example.trbofficerandroid.domain.repository.ApplicationRepository

class RejectApplicationUseCase(
    private val repository: ApplicationRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(id: String): Application {
        val userId = getUserIdUseCase() ?: throw Exception("Пользователь не авторизован")
        return repository.rejectApplication(applicationId = id, userId = userId)
    }
}