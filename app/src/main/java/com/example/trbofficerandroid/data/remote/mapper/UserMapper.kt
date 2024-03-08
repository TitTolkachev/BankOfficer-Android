package com.example.trbofficerandroid.data.remote.mapper

import com.example.trbofficerandroid.GetClientListReply
import com.example.trbofficerandroid.GetOfficerListReply
import com.example.trbofficerandroid.domain.model.UserShort
import com.example.trbofficerandroid.UserShort as UserShortProto

object UserMapper {

    fun GetClientListReply.toDomain(): List<UserShort> {
        return this.clientsList.map { it.toDomain() }
    }

    fun GetOfficerListReply.toDomain(): List<UserShort> {
        return this.officersList.map { it.toDomain() }
    }

    private fun UserShortProto.toDomain(): UserShort {
        return UserShort(
            id = id,
            firstName = firstName,
            lastName = lastName,
            birthDate = birthDate
        )
    }
}