package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.AuthRepository

class UpdateUserIdUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(id: String?) = repository.updateUserId(id)
}