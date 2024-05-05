package com.example.trbofficerandroid.domain.repository

import com.example.trbofficerandroid.domain.model.CreditRating

interface RatingRepository {
    suspend fun updateUserRating(token: String, clientId: String): CreditRating
    suspend fun getUserRating(token: String, clientId: String): CreditRating
}