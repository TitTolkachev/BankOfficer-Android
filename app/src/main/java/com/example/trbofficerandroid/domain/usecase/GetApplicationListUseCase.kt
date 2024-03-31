package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.domain.model.ApplicationShort
import com.example.trbofficerandroid.domain.repository.ApplicationRepository

class GetApplicationListUseCase(
    private val authService: AuthService,
    private val repository: ApplicationRepository
) {
    suspend operator fun invoke(): List<ApplicationShort> {
        val token = authService.getSignedInUser()?.token
            ?: throw Exception("Не авторизован")
        return repository.getApplicationList(token = token)
    }
}