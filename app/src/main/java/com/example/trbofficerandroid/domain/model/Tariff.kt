package com.example.trbofficerandroid.domain.model

data class Tariff(
    val id: String,
    val additionDate: String,
    val name: String,
    val description: String,
    val interestRate: Double,
    val officerId: String,
)
