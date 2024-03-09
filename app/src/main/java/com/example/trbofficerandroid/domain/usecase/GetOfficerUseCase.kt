package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.UserRepository

class GetOfficerUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(id: String) = repository.getOfficer(id)
}