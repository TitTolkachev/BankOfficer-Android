package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.UserRepository

class BlockClientUseCase(
    private val repository: UserRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(id: String) {
        val userId = getUserIdUseCase() ?: throw Exception("Пользователь не авторизован")
        repository.blockClient(clientId = id, officerId = userId)
    }
}