package com.example.trbofficerandroid.domain.repository

import com.example.trbofficerandroid.domain.model.CreateClientShort
import com.example.trbofficerandroid.domain.model.CreateOfficerShort
import com.example.trbofficerandroid.domain.model.UserShort

interface UserRepository {
    suspend fun getClientList(): List<UserShort>
    suspend fun getOfficerList(): List<UserShort>
    suspend fun createClient(client: CreateClientShort)
    suspend fun createOfficer(client: CreateOfficerShort)
}