package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.UserRepository

class BlockOfficerUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(id: String) {
        TODO("Убрал id пользователя")
        val userId = throw Exception("Пользователь не авторизован")
        repository.blockOfficer(officerId = id, whoBlocksId = userId)
    }
}
