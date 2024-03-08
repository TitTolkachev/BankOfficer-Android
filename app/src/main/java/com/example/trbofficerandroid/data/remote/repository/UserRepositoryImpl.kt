package com.example.trbofficerandroid.data.remote.repository

import android.util.Log
import com.example.trbofficerandroid.CreateClientRequest
import com.example.trbofficerandroid.CreateOfficerRequest
import com.example.trbofficerandroid.GetClientListRequest
import com.example.trbofficerandroid.GetOfficerListRequest
import com.example.trbofficerandroid.UserServiceGrpc.UserServiceBlockingStub
import com.example.trbofficerandroid.data.remote.mapper.UserMapper.toDomain
import com.example.trbofficerandroid.domain.model.CreateClientShort
import com.example.trbofficerandroid.domain.model.CreateOfficerShort
import com.example.trbofficerandroid.domain.model.UserShort
import com.example.trbofficerandroid.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(private val api: UserServiceBlockingStub) : UserRepository {
    override suspend fun getClientList(): List<UserShort> = withContext(Dispatchers.IO) {
        val request = GetClientListRequest.newBuilder().build()
        return@withContext try {
            api.getClientList(request).toDomain()
        } catch (e: Exception) {
            Log.e(TAG, e.message ?: "")
            emptyList()
        }
    }

    override suspend fun getOfficerList(): List<UserShort> = withContext(Dispatchers.IO) {
        val request = GetOfficerListRequest.newBuilder().build()
        return@withContext try {
            api.getOfficerList(request).toDomain()
        } catch (e: Exception) {
            Log.e(TAG, e.message ?: "")
            emptyList()
        }
    }

    override suspend fun createClient(client: CreateClientShort) {
        withContext(Dispatchers.IO) {
            val request = CreateClientRequest.newBuilder().build()
            try {
                api.createClient(request)
            } catch (e: Exception) {
                Log.e(TAG, e.message ?: "")
            }
        }
    }

    override suspend fun createOfficer(client: CreateOfficerShort) {
        withContext(Dispatchers.IO) {
            val request = CreateOfficerRequest.newBuilder().build()
            try {
                api.createOfficer(request)
            } catch (e: Exception) {
                Log.e(TAG, e.message ?: "")
            }
        }
    }

    companion object {
        private val TAG = UserRepositoryImpl::class.simpleName
    }
}