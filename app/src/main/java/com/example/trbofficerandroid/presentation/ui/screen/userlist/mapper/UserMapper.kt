package com.example.trbofficerandroid.presentation.ui.screen.userlist.mapper

import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserShort
import com.example.trbofficerandroid.domain.model.UserShort as UserShortDomain

object UserMapper {

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