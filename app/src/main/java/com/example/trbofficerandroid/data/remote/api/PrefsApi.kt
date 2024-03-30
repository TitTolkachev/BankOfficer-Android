package com.example.trbofficerandroid.data.remote.api

import com.example.trbofficerandroid.data.remote.dto.ChangeThemeDto
import com.example.trbofficerandroid.data.remote.dto.ThemeDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface PrefsApi {

    @GET("theme")
    suspend fun getAppTheme(@Query("token") token: String): Response<ThemeDto>

    @PUT("theme")
    suspend fun changeAppTheme(@Body body: ChangeThemeDto): Response<Unit>

    @GET("hidden-accounts")
    suspend fun getHiddenAccounts(@Query("token") token: String): Response<List<String>>
}