package com.example.trbofficerandroid.domain.model

data class CreateTariff(
    val name: String,
    val description: String,
    val interestRate: Double,
    val officerId: String,
)
