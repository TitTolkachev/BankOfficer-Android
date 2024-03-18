package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.UserRepository

class BlockClientUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(id: String) {
        TODO("Убрал id пользователя")
        val userId = throw Exception("Пользователь не авторизован")
        repository.blockClient(clientId = id, officerId = userId)
    }
}