package com.example.trbofficerandroid.data.remote.repository

import android.util.Log
import com.example.trbofficerandroid.GetUserRatingRequest
import com.example.trbofficerandroid.RatingServiceGrpc.RatingServiceBlockingStub
import com.example.trbofficerandroid.UpdateUserRatingRequest
import com.example.trbofficerandroid.data.remote.mapper.RatingMapper.toDomain
import com.example.trbofficerandroid.domain.model.CreditRating
import com.example.trbofficerandroid.domain.repository.RatingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RatingRepositoryImpl(private val api: RatingServiceBlockingStub) :
    RatingRepository {
    override suspend fun updateUserRating(token: String, clientId: String): CreditRating =
        withContext(Dispatchers.IO) {
            val request = UpdateUserRatingRequest.newBuilder()
                .setToken(token)
                .setClientId(clientId)
                .build()
            return@withContext try {
                api.updateUserRating(request).rating.toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при получении кредитного рейтинга пользователя: ${e.message}")
                throw e
            }
        }

    override suspend fun getUserRating(token: String, clientId: String): CreditRating =
        withContext(Dispatchers.IO) {
            val request = GetUserRatingRequest.newBuilder()
                .setToken(token)
                .setClientId(clientId)
                .build()
            return@withContext try {
                api.getUserRating(request).rating.toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при обновлении кредитного рейтинга пользователя: ${e.message}")
                throw e
            }
        }

    companion object {
        private val TAG = TariffRepositoryImpl::class.simpleName
    }
}