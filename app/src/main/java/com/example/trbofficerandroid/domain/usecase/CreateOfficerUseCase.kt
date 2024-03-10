package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.model.CreateOfficer
import com.example.trbofficerandroid.domain.repository.UserRepository

class CreateOfficerUseCase(
    private val repository: UserRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(officer: CreateOfficer) {
        val userId = getUserIdUseCase() ?: throw Exception("Пользователь не авторизован")
        repository.createOfficer(officer.copy(whoCreatedId = userId))
    }
}