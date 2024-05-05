package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.domain.model.CreditRating
import com.example.trbofficerandroid.domain.repository.RatingRepository

class UpdateUserRatingUseCase(
    private val authService: AuthService,
    private val repository: RatingRepository
) {
    suspend operator fun invoke(clientId: String): CreditRating {
        val token = authService.getSignedInUser()?.token
            ?: throw Exception("Не авторизован")
        return repository.updateUserRating(token = token, clientId = clientId)
    }
}