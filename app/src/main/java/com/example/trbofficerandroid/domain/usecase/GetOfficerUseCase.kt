package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.domain.model.Officer
import com.example.trbofficerandroid.domain.repository.UserRepository

class GetOfficerUseCase(
    private val authService: AuthService,
    private val repository: UserRepository
) {
    suspend operator fun invoke(id: String): Officer {
        val token = authService.getSignedInUser()?.token
            ?: throw Exception("Не авторизован")
        return repository.getOfficer(token = token, id = id)
    }
}