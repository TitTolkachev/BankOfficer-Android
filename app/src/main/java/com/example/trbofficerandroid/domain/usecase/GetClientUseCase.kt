package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.UserRepository

class GetClientUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(id: String) = repository.getClient(id)
}