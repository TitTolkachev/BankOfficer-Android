package com.example.trbofficerandroid.domain.model

data class Tariff(
    val id: String,
    val additionDate: Long,
    val name: String,
    val description: String,
    val interestRate: Double,
    val officerId: String,
)
