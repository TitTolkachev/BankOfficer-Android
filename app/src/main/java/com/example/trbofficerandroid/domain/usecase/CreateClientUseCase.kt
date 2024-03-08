package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.model.CreateClient
import com.example.trbofficerandroid.domain.repository.UserRepository

class CreateClientUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(client: CreateClient) = repository.createClient(client)
}