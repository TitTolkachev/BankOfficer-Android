package com.example.trbofficerandroid.presentation.ui.screen.addclient.model

import com.example.trbofficerandroid.domain.model.Sex

data class AddClient(
    val firstName: String = "",
    val lastName: String = "",
    val patronymicName: String = "",
    val birthDate: String = "",
    val birthDateMs: Long = 0,
    val phoneNumber: String = "",
    val address: String = "",
    val passportNumber: String = "",
    val passportSeries: String = "",
    val whoCreatedId: String = "",
    val email: String = "",
    val password: String = "",
    val sex: Sex = Sex.MALE,
)
