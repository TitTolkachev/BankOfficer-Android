package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.model.CreateOfficer
import com.example.trbofficerandroid.domain.repository.UserRepository

class CreateOfficerUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(officer: CreateOfficer) {
        TODO("Убрал id пользователя")
        val userId = throw Exception("Пользователь не авторизован")
        repository.createOfficer(officer.copy(whoCreatedId = userId))
    }
}