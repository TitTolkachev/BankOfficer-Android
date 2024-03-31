package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.domain.model.CreateUser
import com.example.trbofficerandroid.domain.repository.UserRepository

class CreateUserUseCase(
    private val authService: AuthService,
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: CreateUser) {
        val userDate = authService.getSignedInUser() ?: throw Exception("Не авторизован")
        val token = userDate.token ?: throw Exception("Не авторизован")
        val userId = userDate.userId
        repository.createUser(token = token, user.copy(whoCreatedId = userId))
    }
}