package com.example.trbofficerandroid.domain.repository

import com.example.trbofficerandroid.domain.model.CreateClient
import com.example.trbofficerandroid.domain.model.CreateOfficer
import com.example.trbofficerandroid.domain.model.UserShort

interface UserRepository {
    suspend fun getClientList(): List<UserShort>
    suspend fun getOfficerList(): List<UserShort>

    /**
     * @return Created User id.
     */
    suspend fun createClient(user: CreateClient): String

    /**
     * @return Created User id.
     */
    suspend fun createOfficer(user: CreateOfficer): String
}