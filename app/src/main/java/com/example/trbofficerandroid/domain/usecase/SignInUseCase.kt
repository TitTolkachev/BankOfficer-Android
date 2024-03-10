package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.UserRepository

class SignInUseCase(
    private val repository: UserRepository,
    private val updateUserIdUseCase: UpdateUserIdUseCase
) {
    suspend operator fun invoke(email: String, password: String) {
        try {
            val userId = repository.signIn(email = email, password = password)
            updateUserIdUseCase(userId)
        } catch (e: Exception) {
            updateUserIdUseCase(null)
            throw e
        }
    }
}