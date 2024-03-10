package com.example.trbofficerandroid.domain.repository

import com.example.trbofficerandroid.domain.model.Application
import com.example.trbofficerandroid.domain.model.ApplicationShort

interface ApplicationRepository {

    suspend fun getApplicationList(): List<ApplicationShort>
    suspend fun getApplication(id: String): Application
    suspend fun approveApplication(applicationId: String, userId: String): Application
    suspend fun rejectApplication(applicationId: String, userId: String): Application
}