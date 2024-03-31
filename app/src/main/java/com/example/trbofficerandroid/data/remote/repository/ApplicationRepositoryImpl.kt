package com.example.trbofficerandroid.data.remote.repository

import android.util.Log
import com.example.trbofficerandroid.ApplicationServiceGrpc
import com.example.trbofficerandroid.ApproveApplicationRequest
import com.example.trbofficerandroid.GetApplicationListRequest
import com.example.trbofficerandroid.GetApplicationRequest
import com.example.trbofficerandroid.RejectApplicationRequest
import com.example.trbofficerandroid.data.remote.mapper.ApplicationMapper.toDomain
import com.example.trbofficerandroid.domain.model.Application
import com.example.trbofficerandroid.domain.model.ApplicationShort
import com.example.trbofficerandroid.domain.repository.ApplicationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApplicationRepositoryImpl(
    private val api: ApplicationServiceGrpc.ApplicationServiceBlockingStub
) : ApplicationRepository {

    override suspend fun getApplicationList(token: String): List<ApplicationShort> =
        withContext(Dispatchers.IO) {
            val request = GetApplicationListRequest.newBuilder()
                .setToken(token)
                .build()
            return@withContext try {
                api.getApplicationList(request).toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при получении списка заявок на кредиты: ${e.message}")
                throw e
            }
        }

    override suspend fun getApplication(token: String, applicationId: String): Application =
        withContext(Dispatchers.IO) {
            val request = GetApplicationRequest.newBuilder()
                .setToken(token)
                .setId(applicationId)
                .build()
            return@withContext try {
                api.getApplication(request).application.toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при получении заявки на кредит: ${e.message}")
                throw e
            }
        }

    override suspend fun approveApplication(token: String, applicationId: String): Application =
        withContext(Dispatchers.IO) {
            val request = ApproveApplicationRequest.newBuilder()
                .setToken(token)
                .setApplicationId(applicationId)
                .build()
            return@withContext try {
                api.approveApplication(request).application.toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при одобрении заявки на кредит: ${e.message}")
                throw e
            }
        }

    override suspend fun rejectApplication(token: String, applicationId: String): Application =
        withContext(Dispatchers.IO) {
            val request = RejectApplicationRequest.newBuilder()
                .setToken(token)
                .setApplicationId(applicationId)
                .build()
            return@withContext try {
                api.rejectApplication(request).application.toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при отклонении заявки на кредит: ${e.message}")
                throw e
            }
        }

    companion object {
        private val TAG = ApplicationRepositoryImpl::class.simpleName
    }
}