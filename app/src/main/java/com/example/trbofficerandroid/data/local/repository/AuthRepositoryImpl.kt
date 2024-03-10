package com.example.trbofficerandroid.data.local.repository

import com.example.trbofficerandroid.data.local.storage.AuthStorage
import com.example.trbofficerandroid.domain.repository.AuthRepository

class AuthRepositoryImpl(private val storage: AuthStorage) : AuthRepository {
    override suspend fun updateUserId(id: String?) = storage.updateUserId(id)

    override suspend fun getUserId(): String? = storage.getUserId()
}