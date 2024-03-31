package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.domain.model.Application
import com.example.trbofficerandroid.domain.repository.ApplicationRepository

class RejectApplicationUseCase(
    private val authService: AuthService,
    private val repository: ApplicationRepository
) {
    suspend operator fun invoke(id: String): Application {
        val token = authService.getSignedInUser()?.token
            ?: throw Exception("Не авторизован")
        return repository.rejectApplication(token = token, applicationId = id)
    }
}