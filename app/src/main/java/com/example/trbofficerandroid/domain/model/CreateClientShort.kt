package com.example.trbofficerandroid.domain.model

data class CreateClientShort(
    val firstName: String = "",
    val lastName: String = "",
    val patronymicName: String = "",
    val phoneNumber: String = "",
    val address: String = "",
    val passportNumber: String = "",
    val passportSeries: String = "",
    val email: String = "",
    val password: String = "",
)
