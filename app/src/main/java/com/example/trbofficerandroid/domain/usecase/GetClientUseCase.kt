package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.domain.model.Client
import com.example.trbofficerandroid.domain.repository.UserRepository

class GetClientUseCase(
    private val authService: AuthService,
    private val repository: UserRepository
) {
    suspend operator fun invoke(id: String): Client {
        val token = authService.getSignedInUser()?.token
            ?: throw Exception("Не авторизован")
        return repository.getClient(token = token, id = id)
    }
}