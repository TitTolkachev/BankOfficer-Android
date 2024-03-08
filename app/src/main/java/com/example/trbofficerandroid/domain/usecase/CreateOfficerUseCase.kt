package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.model.CreateOfficerShort
import com.example.trbofficerandroid.domain.repository.UserRepository

class CreateOfficerUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(officer: CreateOfficerShort) = repository.createOfficer(officer)
}