package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.domain.repository.UserRepository

class BlockUserUseCase(
    private val authService: AuthService,
    private val repository: UserRepository
) {
    suspend operator fun invoke(userId: String) {
        val token = authService.getSignedInUser()?.token
            ?: throw Exception("Не авторизован")
        repository.blockUser(token = token, userId = userId)
    }
}
