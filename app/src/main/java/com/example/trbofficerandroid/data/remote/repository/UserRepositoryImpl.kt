package com.example.trbofficerandroid.data.remote.repository

import android.util.Log
import com.example.trbofficerandroid.BlockUserRequest
import com.example.trbofficerandroid.CreateUserRequest
import com.example.trbofficerandroid.GetClientListRequest
import com.example.trbofficerandroid.GetClientRequest
import com.example.trbofficerandroid.GetOfficerListRequest
import com.example.trbofficerandroid.GetOfficerRequest
import com.example.trbofficerandroid.UserServiceGrpc.UserServiceBlockingStub
import com.example.trbofficerandroid.data.remote.mapper.UserMapper.toDomain
import com.example.trbofficerandroid.domain.model.Client
import com.example.trbofficerandroid.domain.model.CreateUser
import com.example.trbofficerandroid.domain.model.Officer
import com.example.trbofficerandroid.domain.model.UserShort
import com.example.trbofficerandroid.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(private val api: UserServiceBlockingStub) : UserRepository {

    override suspend fun getClientList(token: String): List<UserShort> =
        withContext(Dispatchers.IO) {
            val request = GetClientListRequest.newBuilder().setToken(token).build()
            return@withContext try {
                api.getClientList(request).toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при получении списка клиентов: ${e.message}")
                throw e
            }
        }

    override suspend fun getOfficerList(token: String): List<UserShort> =
        withContext(Dispatchers.IO) {
            val request = GetOfficerListRequest.newBuilder().setToken(token).build()
            return@withContext try {
                api.getOfficerList(request).toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при получении списка сотрудников: ${e.message}")
                throw e
            }
        }

    override suspend fun getClient(token: String, id: String): Client =
        withContext(Dispatchers.IO) {
            val request = GetClientRequest.newBuilder().setToken(token).setClientId(id).build()
            return@withContext try {
                api.getClient(request).toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при получении клиента: ${e.message}")
                throw e
            }
        }

    override suspend fun getOfficer(token: String, id: String): Officer =
        withContext(Dispatchers.IO) {
            val request = GetOfficerRequest.newBuilder().setToken(token).setOfficerId(id).build()
            return@withContext try {
                api.getOfficer(request).toDomain()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при получении сотрудника: ${e.message}")
                throw e
            }
        }

    override suspend fun blockUser(token: String, userId: String) {
        withContext(Dispatchers.IO) {
            val request = BlockUserRequest.newBuilder()
                .setToken(token)
                .setUserId(userId)
                .build()
            return@withContext try {
                api.blockUser(request)
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при блокировке пользователя: ${e.message}")
                throw e
            }
        }
    }

    override suspend fun createUser(token: String, user: CreateUser): String =
        withContext(Dispatchers.IO) {
            val request = CreateUserRequest.newBuilder().apply {
                setToken(token)
                setFirstName(user.firstName)
                setLastName(user.lastName)
                user.patronymicName?.let { setPatronymic(it) }
                setBirthDate(user.birthDate)
                setPhoneNumber(user.phoneNumber)
                setAddress(user.address)
                setPassportNumber(user.passportNumber)
                user.passportSeries?.let { setPassportSeries(it) }
                setWhoCreatedId(user.whoCreatedId)
                setEmail(user.email)
                setPassword(user.password)
                setSex(user.sex.name)
                setIsClient(user.isClient)
                setIsOfficer(user.isOfficer)
            }.build()
            return@withContext try {
                api.createUser(request).id
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при создании пользователя: ${e.message}")
                throw e
            }
        }

    companion object {
        private val TAG = UserRepositoryImpl::class.simpleName
    }
}