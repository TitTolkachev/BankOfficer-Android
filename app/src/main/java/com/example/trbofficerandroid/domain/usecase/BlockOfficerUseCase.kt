package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.UserRepository

class BlockOfficerUseCase(
    private val repository: UserRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(id: String) {
        val userId = getUserIdUseCase() ?: throw Exception("Пользователь не авторизован")
        repository.blockOfficer(officerId = id, whoBlocksId = userId)
    }
}
