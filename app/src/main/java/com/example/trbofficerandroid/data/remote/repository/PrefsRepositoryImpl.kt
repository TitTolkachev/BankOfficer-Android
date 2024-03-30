package com.example.trbofficerandroid.data.remote.repository

import android.util.Log
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
        } catch (e: Exception) {
            Log.i("PrefsRepository", "getAppTheme: ${e.printStackTrace()}")
            AppTheme.UNSPECIFIED
        }
    }

    suspend fun changeAppTheme(token: String, newTheme: AppTheme) = withContext(Dispatchers.IO) {
        try {
            val request = ChangeThemeDto(token = token, themeDark = newTheme == AppTheme.DARK)
            api.changeAppTheme(request)
        } catch (e: Exception) {
            Log.i("PrefsRepository", "changeAppTheme: ${e.printStackTrace()}")
        }
    }

    suspend fun getHiddenAccounts(token: String): List<String> = withContext(Dispatchers.IO) {
        return@withContext try {
            api.getHiddenAccounts(token).body() ?: emptyList()
        } catch (e: Exception) {
            Log.i("PrefsRepository", "getHiddenAccounts: ${e.printStackTrace()}")
            emptyList()
        }
    }
}