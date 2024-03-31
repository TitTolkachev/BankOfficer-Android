package com.example.trbofficerandroid.data.remote.model

data class UserData(
    val userId: String,
    val firebaseId: String,
    val token: String?,
    val username: String?,
    val profilePictureUrl: String?
)
