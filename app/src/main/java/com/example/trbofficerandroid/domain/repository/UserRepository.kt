package com.example.trbofficerandroid.domain.repository

import com.example.trbofficerandroid.domain.model.Client
import com.example.trbofficerandroid.domain.model.CreateClient
import com.example.trbofficerandroid.domain.model.CreateOfficer
import com.example.trbofficerandroid.domain.model.Officer
import com.example.trbofficerandroid.domain.model.UserShort

interface UserRepository {
    suspend fun signIn(email: String, password: String): String
    suspend fun getClientList(): List<UserShort>
    suspend fun getOfficerList(): List<UserShort>
    suspend fun getClient(id: String): Client
    suspend fun getOfficer(id: String): Officer
    suspend fun blockClient(clientId: String, officerId: String)
    suspend fun blockOfficer(officerId: String, whoBlocksId: String)

    /**
     * @return Created User id.
     */
    suspend fun createClient(user: CreateClient): String

    /**
     * @return Created User id.
     */
    suspend fun createOfficer(user: CreateOfficer): String
}