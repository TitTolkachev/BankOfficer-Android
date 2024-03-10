package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.AuthRepository

class GetUserIdUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke() = repository.getUserId()
}