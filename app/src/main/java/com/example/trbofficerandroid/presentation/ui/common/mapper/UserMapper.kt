package com.example.trbofficerandroid.presentation.ui.common.mapper

import com.example.trbofficerandroid.domain.model.CreateUser
import com.example.trbofficerandroid.presentation.ui.screen.addclient.model.AddUser
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserShort
import com.example.trbofficerandroid.domain.model.UserShort as UserShortDomain

object UserMapper {

    fun AddUser.toDomain(): CreateUser {
        return CreateUser(
            firstName = firstName,
            lastName = lastName,
            patronymicName = patronymicName.ifBlank { null },
            birthDate = birthDateMs,
            phoneNumber = phoneNumber,
            address = address,
            passportNumber = passportNumber,
            passportSeries = passportSeries.ifBlank { null },
            whoCreatedId = whoCreatedId,
            email = email,
            password = password,
            sex = sex,
            isClient = isClient,
            isOfficer = isOfficer,
        )
    }

    fun List<UserShortDomain>.toUi(): List<UserShort> {
        return this.map { it.toUi() }
    }

    private fun UserShortDomain.toUi(): UserShort {
        return UserShort(
            id = id,
            firstName = firstName,
            lastName = lastName,
            birthDate = birthDate,
        )
    }
}