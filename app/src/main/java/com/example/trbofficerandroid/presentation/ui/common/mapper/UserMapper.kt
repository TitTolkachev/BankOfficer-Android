package com.example.trbofficerandroid.presentation.ui.common.mapper

import com.example.trbofficerandroid.domain.model.CreateClient
import com.example.trbofficerandroid.domain.model.CreateOfficer
import com.example.trbofficerandroid.presentation.ui.screen.addclient.model.AddClient
import com.example.trbofficerandroid.presentation.ui.screen.addofficer.model.AddOfficer
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserShort
import com.example.trbofficerandroid.domain.model.UserShort as UserShortDomain

object UserMapper {

    fun AddClient.toDomain(): CreateClient {
        return CreateClient(
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
            sex = sex
        )
    }

    fun AddOfficer.toDomain(): CreateOfficer {
        return CreateOfficer(
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
            sex = sex
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