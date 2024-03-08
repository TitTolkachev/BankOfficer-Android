package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.model.CreateClient
import com.example.trbofficerandroid.domain.repository.UserRepository

class CreateClientUseCase(
    private val repository: UserRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(client: CreateClient) {
        val userId = getUserIdUseCase()
        repository.createClient(client.copy(whoCreatedId = userId))
    }
}