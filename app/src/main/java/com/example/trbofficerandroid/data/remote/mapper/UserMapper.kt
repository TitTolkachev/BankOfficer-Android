package com.example.trbofficerandroid.data.remote.mapper

import com.example.trbofficerandroid.GetClientListReply
import com.example.trbofficerandroid.GetClientReply
import com.example.trbofficerandroid.GetOfficerListReply
import com.example.trbofficerandroid.GetOfficerReply
import com.example.trbofficerandroid.domain.model.Client
import com.example.trbofficerandroid.domain.model.Officer
import com.example.trbofficerandroid.domain.model.Sex
import com.example.trbofficerandroid.domain.model.UserShort
import com.example.trbofficerandroid.UserShort as UserShortProto

object UserMapper {

    fun GetClientReply.toDomain(): Client {
        with(this.client) {
            return Client(
                id = id,
                firstName = firstName,
                lastName = lastName,
                patronymic = patronymic,
                birthDate = birthDate,
                phoneNumber = phoneNumber,
                address = address,
                passportNumber = passportNumber,
                passportSeries = passportSeries,
                email = email,
                sex = if (sex == Sex.MALE.name) Sex.MALE else Sex.FEMALE,
                blocked = blocked,
                whoBlocked = whoBlocked,
                whoCreated = whoCreated,
            )
        }
    }

    fun GetOfficerReply.toDomain(): Officer {
        with(this.officer) {
            return Officer(
                id = id,
                firstName = firstName,
                lastName = lastName,
                patronymic = patronymic,
                birthDate = birthDate,
                phoneNumber = phoneNumber,
                address = address,
                passportNumber = passportNumber,
                passportSeries = passportSeries,
                email = email,
                sex = if (sex == Sex.MALE.name) Sex.MALE else Sex.FEMALE,
                blocked = blocked,
                whoBlocked = whoBlocked,
                whoCreated = whoCreated,
            )
        }
    }

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