package com.example.trbofficerandroid.data.remote.repository

import com.example.trbofficerandroid.data.remote.api.PrefsApi
import com.example.trbofficerandroid.data.remote.dto.ChangeThemeDto
import com.example.trbofficerandroid.domain.model.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PrefsRepositoryImpl(
    private val api: PrefsApi
) {
    suspend fun getAppTheme(token: String): AppTheme = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = api.getAppTheme(token).body()
            when (response?.themeDark) {
                true -> AppTheme.DARK
                false -> AppTheme.LIGHT
                else -> AppTheme.UNSPECIFIED
            }
        } catch (_: Exception) {
            AppTheme.UNSPECIFIED
        }
    }

    suspend fun changeAppTheme(token: String, newTheme: AppTheme) = withContext(Dispatchers.IO) {
        try {
            val request = ChangeThemeDto(token = token, themeDark = newTheme == AppTheme.DARK)
            api.changeAppTheme(request)
        } catch (_: Exception) {
        }
    }

    suspend fun getHiddenAccounts(token: String): List<String> = withContext(Dispatchers.IO) {
        return@withContext try {
            api.getHiddenAccounts(token).body() ?: emptyList()
        } catch (_: Exception) {
            emptyList()
        }
    }
}