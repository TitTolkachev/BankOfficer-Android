package com.example.trbofficerandroid.domain.model

data class Officer(
    val id: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String? = null,
    val birthDate: Long,
    val phoneNumber: String,
    val address: String,
    val passportNumber: String,
    val passportSeries: String? = null,
    val email: String,
    val sex: Sex,
    val blocked: Boolean,
    val whoBlocked: String? = null,
    val whoCreated: String? = null,
)
