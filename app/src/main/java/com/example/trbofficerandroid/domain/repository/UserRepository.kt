package com.example.trbofficerandroid.domain.repository

import com.example.trbofficerandroid.domain.model.Client
import com.example.trbofficerandroid.domain.model.CreateUser
import com.example.trbofficerandroid.domain.model.Officer
import com.example.trbofficerandroid.domain.model.UserShort

interface UserRepository {
    suspend fun getClientList(token: String): List<UserShort>
    suspend fun getOfficerList(token: String): List<UserShort>
    suspend fun getClient(token: String, id: String): Client
    suspend fun getOfficer(token: String, id: String): Officer
    suspend fun blockUser(token: String, userId: String)

    /**
     * @return Created User id.
     */
    suspend fun createUser(token: String, user: CreateUser): String
}