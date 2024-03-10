package com.example.trbofficerandroid.domain.repository

interface AuthRepository {
    suspend fun updateUserId(id: String?)
    suspend fun getUserId(): String?
}