package com.example.trbofficerandroid.presentation.ui.screen.userlist.model

import com.example.trbofficerandroid.UserShort as ProtoUserShort

data class UserShort(
    val id: String,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
) {
    companion object {
        fun fromProto(item: ProtoUserShort): UserShort {
            return UserShort(
                id = item.id,
                firstName = item.firstName,
                lastName = item.lastName,
                birthDate = item.birthDate,
            )
        }
    }
}
