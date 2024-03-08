package com.example.trbofficerandroid.data.remote.repository

import android.util.Log
import com.example.trbofficerandroid.CreateClientRequest
import com.example.trbofficerandroid.CreateOfficerRequest
import com.example.trbofficerandroid.GetClientListRequest
import com.example.trbofficerandroid.GetOfficerListRequest
import com.example.trbofficerandroid.UserServiceGrpc.UserServiceBlockingStub
import com.example.trbofficerandroid.data.remote.mapper.UserMapper.toDomain
import com.example.trbofficerandroid.domain.model.CreateClient
import com.example.trbofficerandroid.domain.model.CreateOfficer
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

    override suspend fun createClient(user: CreateClient): String = withContext(Dispatchers.IO) {
        val request = CreateClientRequest.newBuilder().apply {
            setFirstName(user.firstName)
            setLastName(user.lastName)
            user.patronymicName?.let { setPatronymic(it) }
            setBirthDate(user.birthDate)
            setPhoneNumber(user.phoneNumber)
            setAddress(user.address)
            setPassportNumber(user.passportNumber)
            user.passportSeries?.let { setPassportSeries(it) }
            setWhoCreatedId("3fa85f64-5717-4562-b3fc-2c963f66afa6")
            setEmail(user.email)
            setPassword(user.password)
            setSex(user.sex.name)
        }.build()
        return@withContext try {
            val reply = api.createClient(request)
            if (!reply.error.isNullOrBlank())
                throw Exception(reply.error ?: "Ошибка при создании пользователя")
            reply.id
        } catch (e: Exception) {
            Log.e(TAG, e.message ?: "Ошибка при создании пользователя")
            throw e
        }
    }

    override suspend fun createOfficer(user: CreateOfficer): String = withContext(Dispatchers.IO) {
        val request = CreateOfficerRequest.newBuilder().apply {
            setFirstName(user.firstName)
            setLastName(user.lastName)
            user.patronymicName?.let { setPatronymic(it) }
            setBirthDate(user.birthDate)
            setPhoneNumber(user.phoneNumber)
            setAddress(user.address)
            setPassportNumber(user.passportNumber)
            user.passportSeries?.let { setPassportSeries(it) }
            setWhoCreatedId("3fa85f64-5717-4562-b3fc-2c963f66afa6")
            setEmail(user.email)
            setPassword(user.password)
            setSex(user.sex.name)
        }.build()
        return@withContext try {
            val reply = api.createOfficer(request)
            if (!reply.error.isNullOrBlank())
                throw Exception(reply.error)
            reply.id
        } catch (e: Exception) {
            Log.e(TAG, e.message ?: "Ошибка при создании сотрудника")
            throw e
        }
    }

    companion object {
        private val TAG = UserRepositoryImpl::class.simpleName
    }
}