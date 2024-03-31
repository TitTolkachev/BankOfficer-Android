package com.example.trbofficerandroid.domain.repository

import com.example.trbofficerandroid.domain.model.Application
import com.example.trbofficerandroid.domain.model.ApplicationShort

interface ApplicationRepository {
    suspend fun getApplicationList(token: String): List<ApplicationShort>
    suspend fun getApplication(token: String, applicationId: String): Application
    suspend fun approveApplication(token: String, applicationId: String): Application
    suspend fun rejectApplication(token: String, applicationId: String): Application
}