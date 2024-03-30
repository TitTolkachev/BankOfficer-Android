package com.example.trbofficerandroid.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ChangeThemeDto(
    val token: String,
    val themeDark: Boolean,
)
