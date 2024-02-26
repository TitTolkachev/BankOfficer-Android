package com.example.trbofficerandroid.presentation.ui.screen.client.model

data class Client(
    val id: String,
    val firstName: String,
    val lastName: String,
    val patronymicName: String?,
    val phoneNumber: String,
    val address: String,
    val passportNumber: String,
    val passportSeries: String,
    val isBlocked: Boolean,
    val whoBlocked: String?,
    val whoCreated: String,
)
